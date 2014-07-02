package com.byoutline.eventcallback;

import java.util.Collections;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/**
 * Callback that stores project wide settings. It is suggested to Inject it into
 * classes that need ability to create callbacks.
 *
 * @author Sebastian Kacprzak <nait at naitbit.com> on 26.06.14.
 */
public class CallbackConfig {

    final boolean debug;
    final Bus bus;
    final Provider<String> sessionIdProvider;
    final Map<Class, SuccessHandler> sharedSuccessHandlers;

    /**
     * Creates instance of default config for callbacks. Uses session provider
     * that always return same session, so there will be no difference between
     * sameSessionOnly and multiSessions events. Debug checks will be turned
     * off.
     *
     * @param bus bus on which callback events will be posted.
     */
    public CallbackConfig(@Nonnull Bus bus) {
        this(false, bus);
    }

    /**
     *
     * Creates instance of default config for callbacks. Uses session provider
     * that always return same session, so there will be no difference between
     * sameSessionOnly and multiSessions events.
     *
     * @param debug true if extra checks should be on.
     * @param bus bus on which callback events will be posted.
     */
    public CallbackConfig(boolean debug, @Nonnull Bus bus) {
        this(debug, bus, new Provider<String>() {

            @Override
            public String get() {
                return "";
            }

        });
    }

    /**
     * Creates instance of default config for callbacks. 
     * 
     * @param debug true if extra checks should be on.
     * @param bus bus on which callback events will be posted.
     * @param sessionIdProvider provides information about current session.
     * If same string is returned from two cals it is considered to be same session.
     */
    public CallbackConfig(boolean debug, @Nonnull Bus bus,
            @Nonnull Provider<String> sessionIdProvider) {
        this(debug, bus, sessionIdProvider, Collections.EMPTY_MAP);
    }

    /**
     * Creates instance of default config for callbacks. 
     * 
     * @param debug true if extra checks should be on.
     * @param bus bus on which callback events will be posted.
     * @param sessionIdProvider provides information about current session.
     * If same string is returned from two calls it is considered to be same session.
     * @param sharedSuccessHandlers maps success responses from server with
     * {@link SuccessHandler}s so common operation for given result type can
     * be handled globally in whole project.
     */
    public CallbackConfig(boolean debug, @Nonnull Bus bus,
            @Nonnull Provider<String> sessionIdProvider,
            @Nonnull Map<Class, SuccessHandler> sharedSuccessHandlers) {
        this.debug = debug;
        this.bus = bus;
        this.sessionIdProvider = sessionIdProvider;
        this.sharedSuccessHandlers = sharedSuccessHandlers;
    }
}