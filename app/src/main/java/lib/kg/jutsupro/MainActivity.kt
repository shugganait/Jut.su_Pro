package lib.kg.jutsupro


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import lib.kg.jutsupro.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWebView()
        checkInternet()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() = with(binding) {
        webView.webViewClient = WebViewClient()
        webView.apply {
            loadUrl("https://jut.su/")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        binding.apply {
            if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
        }
        Toast.makeText(this, "Загрузка...", Toast.LENGTH_SHORT).show()
    }

    private fun checkInternet() {
        ConnectionLiveData(application).observe(this) {
            binding.noInternet.isVisible = !it
            binding.webView.isVisible = it
        }
    }
}