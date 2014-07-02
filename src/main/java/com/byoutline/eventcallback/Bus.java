package com.byoutline.eventcallback;

/**
 * Bus on which all events from {@link EventCallback} will be posted.
 * 
 * @author Sebastian Kacprzak <nait at naitbit.com>
 */
public interface Bus {
    
    void post(Object event);
    void postSticky(Object event);
}
