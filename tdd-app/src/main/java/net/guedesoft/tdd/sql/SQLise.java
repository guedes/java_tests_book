package net.guedesoft.tdd.sql;

import java.util.ArrayList;


public class SQLise
{
    private String sqlOriginal;
    private String where;
    private ArrayList<String[]> joins = new ArrayList<String[]> ();

    public SQLise(String sql)
    {
	this.sqlOriginal = sql;
    }

    public String getSQLOriginal()
    {
	return this.sqlOriginal;
    }

    public SQLise where(String where)
    {
	this.where = where;
        return this;
    }

    public SQLise join(String join, String using)
    {
	this.joins.add( (String[]) new String[] { "a", "b" } );
	return this;
    }

    public String getWhere()
    {
	if (this.where != null)
	    return " WHERE " + this.where;
	else
	    return "";
    }

    public String getJoins()
    {
	String sql = "";

	if (this.joins != null)
	    for (String join : this.joins)
		sql += " JOIN (" +
			    join[0] +
			    ") as t1 USING (" + join[1] + ")";

	return sql;
    }

    public String toSQL()
    {
	String sql = getSQLOriginal();

        sql += getWhere();

	return sql;
    }
}
