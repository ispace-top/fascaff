package top.ispace.fascaff.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import top.ispace.fascaff.demo.databinding.ActivityMainBinding
import top.ispace.fascaff.log.Log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var data = mapOf<String,String>("name" to "lkjl","dsfsd" to "dfdsfs","sdfsd" to "dfsfds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("activity create!");
        binding.tvTitle.setOnClickListener{
            Log.d("Title Btn is Clicked!")
        }
        binding.tvBtn.setOnClickListener{
            Log.d(213213.23f,"sdfdsf",data)
        }

    }
}