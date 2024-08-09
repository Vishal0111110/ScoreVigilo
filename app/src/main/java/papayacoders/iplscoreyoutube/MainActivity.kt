package papayacoders.iplscoreyoutube

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import papayacoders.iplscoreyoutube.adapter.MatchAdapter
import papayacoders.iplscoreyoutube.api.ApiInterface
import papayacoders.iplscoreyoutube.api.ApiUtilities
import papayacoders.iplscoreyoutube.constant.Constant.API_KEY
import papayacoders.iplscoreyoutube.constant.Constant.SERIES_ID
import papayacoders.iplscoreyoutube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val matchApi = ApiUtilities.getInstance().create(ApiInterface::class.java)


        lifecycleScope.launch(Dispatchers.IO) {

            val result = matchApi.getSeries(API_KEY, SERIES_ID)

            if (result.body() != null) {
                Log.d("SHUBH", "onCreate: ${result.body()!!.data.matchList}")

                withContext(Dispatchers.Main) {
                    binding.progressbar.visibility = GONE
                    binding.recyclerView.adapter =
                        MatchAdapter(this@MainActivity, result.body()!!.data.matchList)
                }
            }
        }
    }
}