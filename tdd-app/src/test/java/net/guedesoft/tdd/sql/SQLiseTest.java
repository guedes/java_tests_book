package net.guedesoft.tdd.sql;

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import net.guedesoft.tdd.sql.SQLise;
import java.util.ArrayList;
import java.util.Arrays;


public class SQLiseTest
{
    public static final String CONSTANTE_ESPERADA = "SELECT 1+1 "   +
						    "UNION "	    +
						    "SELECT 2";

    @Before public void prepare()
    {

    }

    @Test public void retornaSQLOriginal()
    {
	String sqlEsperado = "SELECT 1+1";

	SQLise meuSQL = new SQLise("SELECT 1+1");

	assertThat(meuSQL.getSQLOriginal(), is(sqlEsperado));
    }

    @Test public void retornaSQLOriginalUtilizandoConstante()
    {
	SQLise meuSQL = new SQLise(CONSTANTE_ESPERADA);

	assertThat(meuSQL.getSQLOriginal(), is(CONSTANTE_ESPERADA));
    }

    @Test public void acrescentaClausulaWhere()
    {
	String sqlEsperado = "SELECT a, b FROM foo WHERE bar = baz";

	SQLise meuSQL = new SQLise("SELECT a, b FROM foo");

	assertThat(meuSQL.getWhere(), is(""));

	assertThat(
		meuSQL
		    .where("foo = bar")
		    .getWhere(),
		is(" WHERE foo = bar"));

	assertThat(
		meuSQL
		    .where("bar = baz")
		    .toSQL(),
		is(sqlEsperado)
	);
    }

    @Test public void acrescentaClausulaJoin()
    {
	String sqlEsperado = "SELECT a, b FROM foo JOIN (SELECT a, bar FROM baz WHERE foobar > 10) as t1 USING (a)";
	ArrayList<String> joinsEsperados = geraListaJoinsEsperados();

	SQLise meuSQL = new SQLise("SELECT a, b FROM foo");

	assertThat(
		meuSQL
		    .join("SELECT a, b FROM c WHERE d = e", "a,b")
		    .join("SELECT x, y FROM z WHERE (d,s) = (f,g)", "x")
		    .getJoins(),
		    is(joinsEsperados));

	assertThat(
		meuSQL
		    .join("SELECT a, bar FROM baz WHERE foobar > 10", "a")
		    .toSQL(),
		is(sqlEsperado)
	);
    }

    private ArrayList<String> geraListaJoinsEsperados()
    {
	ArrayList<String> arr = new ArrayList<String>();
	arr.add("SELECT a, b FROM c WHERE d = e");
	arr.add("SELECT x, y FROM z WHERE (d,s) = (f,g)");

	return arr;
    }
}
