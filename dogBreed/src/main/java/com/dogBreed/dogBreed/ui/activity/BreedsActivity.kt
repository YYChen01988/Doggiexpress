package com.dogBreed.dogBreed.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.dogBreed.R
import com.dogBreed.databinding.ActivityBreedsBinding
import com.dogBreed.dogBreed.ui.fragment.BreedsFragment

class BreedsActivity : AppCompatActivity() {
    private lateinit var activityBreedsBinding: ActivityBreedsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBreedsBinding = ActivityBreedsBinding.inflate(layoutInflater)
        val view = activityBreedsBinding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<BreedsFragment>(R.id.fragmentContainerView)
            }
        }
    }
}
