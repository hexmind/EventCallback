EventCallback
=============
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.byoutline.eventcallback/eventcallback/badge.svg?style=flat)](http://mvnrepository.com/artifact/com.byoutline.eventcallback/eventcallback)
[![Build Status](https://travis-ci.org/byoutline/EventCallback.svg?branch=master)](https://travis-ci.org/byoutline/EventCallback)

EventCallback allows creating instances of [Retrofit](http://square.github.io/retrofit/) [callbacks](http://square.github.io/retrofit/javadoc/retrofit/Callback.html) using short readable syntax.

Instead of creating anonymous classes manually (where you have to take care of not using parent class fields that can change by the time server response arrives)
```java
new Callback<SuccessDTO>() {

    @Override
    public void success(SuccessDTO s, Response response) {
        boolean stillSameSession = myCodeCheckingIfItIsStillSameSession();
        if(stillSameSession) {
            bus.post(new MyEvent());
            bus.post(new SuccessEvent());
        }
    }

    @Override
    public void failure(RetrofitError error) {
        RestErrorWithMsg restErrorWithMsg = myCodeThatTriesToConvertRetrofitErrorToReasonCallFailed(error);
        bus.post(new LoginValidationFailedEvent(restErrorWithMsg));
    }
};
```
you can use EventCallback like this:
```java
EventCallback.<SuccessDTO>builder(config, new TypeToken<RestErrorWithMsg>(){})
    .onSuccess().postEvents(new MyEvent(), new SuccessEvent()).validThisSessionOnly()
    .onError().postResponseEvents(new LoginValidationFailedEvent()).validBetweenSessions()
    .build();
``` 

