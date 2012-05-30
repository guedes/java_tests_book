package net.guedesoft.tdd.app;

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import net.guedesoft.tdd.app.Calc;

public class CalcTest
{
    @Test public void sumValues()
    {
	int total1 = Calc.sum(1,2);
	int total2 = Calc.sum(-1,2);

	assertThat(total1, is(3));
	assertThat(total2, is(1));
    }
}
