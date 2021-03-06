package com.byoutline.eventcallback.internal.actions;

import com.byoutline.eventcallback.EventCallback;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Stores events that can be invoked on {@link EventCallback} creation.
 *
 * @author Sebastian Kacprzak <sebastian.kacprzak at byoutline.com>
 */
public class CreateEvents {
    public final List events;

    public CreateEvents() {
        this(new ArrayList<Object>());
    }

    public CreateEvents(@Nonnull List events) {
        this.events = events;
    }

    void validate() {
        Validate.noNullElements(events);
    }

    @Override
    public String toString() {
        return "CreateEvents{" + "events=" + events + '}';
    }
}
