package com.argaed.pruebaedgar.clean.core

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
abstract class Failure {

    /**
     * Extend this class for feature specific failures.
     */
    abstract class FeatureFailure: Failure()

}