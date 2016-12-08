package id.maskipli.com.movies.Util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by hidayat on 11/17/16.
 */

public class RequestUtil {

    private OkHttp3Stack mOkHttp3Stack;
    public void getData(Context context, String url, final RequestResult requestResult) {
        mOkHttp3Stack = new OkHttp3Stack();
        RequestQueue requestQueue = Volley.newRequestQueue(context, mOkHttp3Stack);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                requestResult.resultData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestResult.errorResultData(error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }
}
