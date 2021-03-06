package com.example.carlos.forecastapp.ui.weather.future.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carlos.forecastapp.R
import com.example.carlos.forecastapp.data.db.LocalDateConverter
import com.example.carlos.forecastapp.internal.toFutureWeatherItems
import com.example.carlos.forecastapp.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.future_list_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.threeten.bp.LocalDate


class FutureListWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: FutureWeatherViewModelFactory by instance()


    private lateinit var viewModel: FutureListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FutureListWeatherViewModel::class.java)

        initViews()
        bindUI()
    }

    private fun initViews() {
        updateSupportActionBarSubtitle(getString(R.string.text_7_days))
        group_loading.visibility = View.VISIBLE
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val weatherLocation = viewModel.weatherLocation.await()
        val futureWeather = viewModel.futureWeatherList.await()

        weatherLocation.observe(this@FutureListWeatherFragment, Observer { location ->
            if (location == null) return@Observer

            updateSupportActionBarTitleLocation(location.name)
        })

        futureWeather.observe(this@FutureListWeatherFragment, Observer { futureWeatherList ->
            if (futureWeatherList == null) return@Observer

            group_loading.visibility = View.GONE

            initRecyclerView(futureWeatherList.toFutureWeatherItems())
        })

    }

    private fun updateSupportActionBarTitleLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateSupportActionBarSubtitle(text: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = text
    }

    private fun initRecyclerView(items: List<FutureWeatherItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? FutureWeatherItem)?.let {
                showWeatherDetail(it.weatherEntry.date, view)
            }
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@FutureListWeatherFragment.context)
            adapter = groupAdapter
        }

    }

    private fun showWeatherDetail(date: LocalDate, view: View) {
        val dateString = LocalDateConverter.dateToString(date)!!
        val actionDetail = FutureListWeatherFragmentDirections.actionDetail(dateString)
        Navigation.findNavController(view).navigate(actionDetail)
    }

}
