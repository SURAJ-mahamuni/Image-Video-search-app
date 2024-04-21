package com.phntechnology.basestructure.fileDownloader

interface Downloader {
    fun downloadFile(url: String,filename : String): Long
}