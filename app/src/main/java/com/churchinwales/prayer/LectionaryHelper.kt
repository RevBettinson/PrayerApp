package com.churchinwales.prayer

import android.content.Context
import java.util.Calendar
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId


class LectionaryHelper {

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


    fun getSeason(context: Context, dateToFind: Calendar): (Array<String>?) {
        val lectionaryFile: String = "LectionaryDates.json"

        val lectionaryJSON: JSONObject;
        val jsonData: String? = getJsonDataFromAsset(context, lectionaryFile)
        if (jsonData != null) {
            lectionaryJSON = JSONObject(jsonData);
        } else {
            return null
        }

/*
        val year: Int = dateToFind.getGregorianYear();
        val month: Int = dateToFind.getGregorianMonth();
        val day: Int = dateToFind.getGregorianDayOfMonth();
*/
        val year: Int = dateToFind.get(Calendar.YEAR)
        val month: Int = dateToFind.get(Calendar.MONTH)
        val day: Int = dateToFind.get(Calendar.DAY_OF_MONTH)

        AppDebug.log(
            "[DEBUG]",
            "Starting Date:" + day.toString() + " " + month.toString() + " " + year.toString()
        )
        var chosenSeason = "TRINITY"
        var week = "1"

        val lectionaryYear: JSONObject = lectionaryJSON.getJSONObject(year.toString());
        val jsonKeys: Iterator<String> = lectionaryYear.keys();


        while (jsonKeys.hasNext()) {
            val currentKey = jsonKeys.next()
            //val jsonObject = lectionaryJSON.getJSONObject("seasonName")
            //val seasonName = jsonObject.optString("Name")
            if (lectionaryYear.get(currentKey) is JSONObject) {
                val season = lectionaryYear.getJSONObject(currentKey)
                AppDebug.log("[DEBUG]", season.toString())
                val seasonName = season.optString("Name")
                AppDebug.log("[DEBUG]Season Object Name", seasonName.toString())
                //val season = lectionaryJSON.getJSONObject(seasonName.toString())
                val seasonMonth = season.optString("StartMonth").toInt()
                val seasonDay = season.optString("StartDay").toInt()
                val seasonEndMonth = season.optString("EndMonth").toInt()
                val seasonEndDay = season.optString("EndDay").toInt()
                if ((month >= seasonMonth) && (month <= seasonEndMonth)) {
                    if ((seasonDay >= day) && (day <= seasonEndDay)) {
                        chosenSeason = seasonName;
                        val date = dateToFind.time
                        val ld: LocalDate =
                            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        val seasonStart: LocalDate = LocalDate.of(year, seasonMonth, seasonDay)

                        val period = Period.between(seasonStart, ld)

                        week = (period.days / 7).toString()

                        AppDebug.log("DaysAndSeasons", chosenSeason + " " + week)


                    }
                }
            }

        }
        AppDebug.log("[DEBUG]", chosenSeason + " " + week)
        return arrayOf(chosenSeason, week);
    }
}