package com.prasoon.atlysandroid.di

import javax.inject.Qualifier

class Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiServiceMovies

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiServiceImages
}