package com.example.carlos.forecastapp.internal

import java.io.IOException


class NoConnectivityException : IOException()

class LocationPermissionNotGrantedException : Exception()