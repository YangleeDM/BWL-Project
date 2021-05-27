package com.example.bwlprojectlistsandmaps.Model

import com.google.gson.annotations.SerializedName

data class CovidResponseDataClass(

	@field:SerializedName("Message")
	val message: String,

	@field:SerializedName("Countries")
	val countries: List<CountriesItem>,

	@field:SerializedName("ID")
	val iD: String,

	@field:SerializedName("Global")
	val global: Global,

	@field:SerializedName("Date")
	val date: String
)

data class CountriesItem(

	@field:SerializedName("NewRecovered")
	val newRecovered: Int,

	@field:SerializedName("NewDeaths")
	val newDeaths: Int,

	@field:SerializedName("TotalRecovered")
	val totalRecovered: Int,

	@field:SerializedName("TotalConfirmed")
	val totalConfirmed: Int,

	@field:SerializedName("Country")
	val country: String,

	@field:SerializedName("Premium")
	val premium: Premium,

	@field:SerializedName("ID")
	val iD: String,

	@field:SerializedName("CountryCode")
	val countryCode: String,

	@field:SerializedName("Slug")
	val slug: String,

	@field:SerializedName("NewConfirmed")
	val newConfirmed: Int,

	@field:SerializedName("TotalDeaths")
	val totalDeaths: Int,

	@field:SerializedName("Date")
	val date: String
)

data class Premium(
	val any: Any? = null
)

data class Global(

	@field:SerializedName("NewRecovered")
	val newRecovered: Int,

	@field:SerializedName("NewDeaths")
	val newDeaths: Int,

	@field:SerializedName("TotalRecovered")
	val totalRecovered: Int,

	@field:SerializedName("TotalConfirmed")
	val totalConfirmed: Int,

	@field:SerializedName("NewConfirmed")
	val newConfirmed: Int,

	@field:SerializedName("TotalDeaths")
	val totalDeaths: Int,

	@field:SerializedName("Date")
	val date: String
)
