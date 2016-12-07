package id.maskipli.com.movies;

import android.content.Context;

import org.junit.Test;

import id.maskipli.com.movies.Util.MovieConstants;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    Context context ;


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void cekUrl () throws Exception{
        String getSearch = MovieConstants.getRecomendation("550");
        System.out.println(getSearch);
    }



}