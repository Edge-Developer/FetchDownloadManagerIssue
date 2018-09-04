package com.edgedevstudio.fetchdownloadmanagerissue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tonyodev.fetch2.*
import com.tonyodev.fetch2core.DownloadBlock

class MainActivity : AppCompatActivity() {

    val fetchListener = object : FetchListener {

        override fun onAdded(download: Download) {
            Log("FetchListener. onAdded")
        }
        override fun onQueued(download: Download, waitingOnNetwork: Boolean) {
            Log("FetchListener. onQueued")
            /*if (request.id == download.id) {
                // showDownloadInList(download)
            }*/
        }

        override fun onWaitingNetwork(download: Download) {
            Log("FetchListener. onWaitingNetwork")
        }

        override fun onCompleted(download: Download) {
            Log("FetchListener. onCompleted")
        }

        override fun onError(download: Download, error: com.tonyodev.fetch2.Error, throwable: Throwable?) {
            Log("FetchListener. onError. Error = $error, Download = $download")
        }


        override fun onDownloadBlockUpdated(download: Download, downloadBlock: DownloadBlock, totalBlocks: Int) {
            Log("FetchListener. onDownloadBlockUpdated")
        }

        override fun onStarted(download: Download, downloadBlocks: List<DownloadBlock>, totalBlocks: Int) {
            Log("FetchListener. onStarted")
        }
        override fun onProgress(download: Download, etaInMilliSeconds: Long, downloadedBytesPerSecond: Long) {
            /* if (request.id == download.id) {
                 // updateDownload(download, etaInMilliSeconds)
             }*/
            val progress = download.progress
            Log("FetchListener. onProgress")
        }

        override fun onPaused(download: Download) {
            Log("FetchListener. onPaused")
        }

        override fun onResumed(download: Download) {
            Log("FetchListener. onResumed")
        }

        override fun onCancelled(download: Download) {
            Log("FetchListener. onCancelled")
        }

        override fun onRemoved(download: Download) {
            Log("FetchListener. onRemoved")
        }

        override fun onDeleted(download: Download) {
            Log("FetchListener. onDeleted")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "http://www.alexszepietowski.com/wp-content/uploads/downloads/2013/03/The%202%20Golden%20Rules%20of%20Property,%20Business%20and%20Life!.pdf"
        val fileName = "MyFile.pdf"
        val dirPath = "/FileDownloader/PDF/$fileName"

        val fetchConfiguration = FetchConfiguration.Builder(this)
                .setDownloadConcurrentLimit(3)
                .build()

        val fetch = Fetch.getInstance(fetchConfiguration)

        val request = Request(url, dirPath)
        request.priority = Priority.HIGH
        request.networkType =  NetworkType.ALL
        request.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG")

        fetch.addListener(fetchListener)

        fetch.enqueue(request)
    }

    fun Log(msg: String) {
        android.util.Log.d("MainActivity", msg)
    }
}
