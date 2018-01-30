package com.example.hrudya.volleyphp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hrudya on 23/1/18.
 */

public class Volleysingleton {

    private static Volleysingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private Volleysingleton(Context context)
    {
        mCtx=context;
        requestQueue = getRequestQueue();
    }

    public static synchronized Volleysingleton getmInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new Volleysingleton(context);
        }
        return mInstance;

    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return requestQueue;
    }
    public <T>void addTorequestque(Request<T> request)
    {
        requestQueue.add(request);

    }
}


