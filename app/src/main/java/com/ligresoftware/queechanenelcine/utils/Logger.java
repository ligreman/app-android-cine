package com.ligresoftware.queechanenelcine.utils;

import android.util.Log;

import com.ligresoftware.queechanenelcine.Constants;

public class Logger {
    /**
     * Log de debug
     */
    public static void d(String category, String message) {
        if (Constants.IS_DEBUG_ENABLED)
            Log.d(category, message);
    }

    /**
     * Log de warning
     */
    public static void w(String category, String message) {
        if (Constants.IS_DEBUG_ENABLED)
            Log.w(category, message);
    }

    /**
     * Log de verbose
     */
    public static void v(String category, String message) {
        if (Constants.IS_DEBUG_ENABLED)
            Log.v(category, message);
    }

    /**
     * Log de info
     */
    public static void i(String category, String message) {
        if (Constants.IS_DEBUG_ENABLED)
            Log.i(category, message);
    }

    /**
     * Log de error
     */
    public static void e(String category, String message) {
        if (Constants.IS_DEBUG_ENABLED)
            Log.e(category, message);
    }

    public static void e(String category, String message, Throwable trow) {
        if (Constants.IS_DEBUG_ENABLED)
            Log.e(category, message, trow);
    }
}
