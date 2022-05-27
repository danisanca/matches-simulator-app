package com.example.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.simulator.databinding.ActivityDetailBinding
import com.example.simulator.domain.Match

class DetailActivity : AppCompatActivity() {
    object Extras{
        const val MATCH ="EXTRA_MATCH"
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtre()
    }

    private fun loadMatchFromExtre() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let{
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            supportActionBar?.title = it.place.name
            binding.tvDescription.text =it.description
            Glide.with(this).load(it.homeTeam.image).into(binding.ivHomeTeam)
            Glide.with(this).load(it.awayTeam.image).into(binding.ivAwayTeam)
            binding.tvHomeTeamName.text = it.homeTeam.name
            binding.tvAwayTeamName.text = it.awayTeam.name
            binding.rbHomeTeamStars.rating = it.homeTeam.starts.toFloat()
            binding.rbAwayTeamStars.rating = it.awayTeam.starts.toFloat()
            if(it.homeTeam.score != null){
                binding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            }
            if(it.awayTeam.score != null){
                binding.tvAwayTeamScore.text = it.awayTeam.score.toString()
            }

        }
    }
}