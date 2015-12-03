package com.mrebollob.loteriadenavidad.app.di.qualifiers.data;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author mrebollob
 */
@Qualifier
@Retention(RUNTIME)
public @interface RetrofitLog {
}