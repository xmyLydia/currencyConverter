package com.example.demo.common;

import com.example.demo.exceptions.RestNotFoundException;

/**
 * @author mingyux
 */
public class RestUtils {
    public static boolean notNull(Object obj) throws RestNotFoundException {
        if (obj == null) {
            throw new RestNotFoundException("not found");
        }
        return true;
    }
}
