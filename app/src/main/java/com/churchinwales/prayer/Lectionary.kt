package com.churchinwales.prayer

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.time.DayOfWeek
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters


class Lectionary {

    companion object {
        const val SEASON: Int = 0
        const val WEEKOFSEASON: Int = 1
    }


    fun getDayOfWeek() : String
    {
        return getDayOfWeek(Calendar.getInstance())
    }

    fun getDayOfWeek(cal: Calendar): String
    {
        //val cal = Calendar.getInstance()
        val formatter: SimpleDateFormat = SimpleDateFormat("EEEE")
        val rDayOfWeek: String =formatter.format(cal)

        AppDebug.log("TAG","Day Of The Week:"+rDayOfWeek)

        return rDayOfWeek;

    }

    fun findPreviousSunday(cal: Calendar) : Calendar {

        var temporalAdjuster = TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)

        var date_temp = cal.time
        var instant_temp = date_temp.toInstant()
        var zonedDateTime = instant_temp.atZone(ZoneId.systemDefault())
        var findSunday = zonedDateTime.toLocalDate()
        findSunday = findSunday.with(temporalAdjuster)
        AppDebug.log(
            "TAG",
            "Find Sunday Year: " + findSunday.year + " Month value:" + findSunday.monthValue + " Day:" + findSunday.dayOfMonth
        )
        //LocalDateTime is NOT 0 rated when it comes to months.
        cal.set(
            findSunday.year,
            findSunday.monthValue - 1,
            findSunday.dayOfMonth
        )
        return cal
    }

    fun getSeason(): Array<String> {
        return getSeason(android.icu.util.Calendar.getInstance())
    }

    /*
    * Calendar Note: Calendar.set methods 0 set the month. That is, January =0. So all the
    * months are one month *less* than expected.
     */
    fun getSeason(cal :Calendar): Array<String> {
        val tz = android.icu.util.TimeZone.getDefault()

        //var cal = android.icu.util.Calendar.getInstance()
        cal.firstDayOfWeek = android.icu.util.Calendar.SUNDAY
        //cal.set(2022,11,1)
        cal.timeZone = tz

        var easter = android.icu.util.Calendar.getInstance()
        easter.set(2022, 4, 9)
        easter.timeZone =tz

        if (cal[android.icu.util.Calendar.YEAR] == 2022) {
            easter.set(2022, 2, 17)
        }
        if (cal[android.icu.util.Calendar.YEAR] == 2023) {
            easter.set(2023, 3, 9)
        }

        val christmas = android.icu.util.Calendar.getInstance()
        christmas.set(Calendar.getInstance().get(java.util.Calendar.YEAR), 11, 25)
        christmas.timeZone=tz

        var season = "ADVENT"
        var weekOfSeason = 1
        var dayOfWeek = "Monday"

        dayOfWeek = Lectionary().getDayOfWeek(cal)


        val adventDate = android.icu.util.Calendar.getInstance(tz)
        adventDate.timeInMillis = christmas.timeInMillis
        adventDate.add(android.icu.util.Calendar.WEEK_OF_YEAR, -4)
        adventDate.firstDayOfWeek = android.icu.util.Calendar.SUNDAY
        adventDate.timeInMillis = findPreviousSunday(adventDate).timeInMillis

        val kingdomDate = android.icu.util.Calendar.getInstance(tz);
        kingdomDate.timeInMillis = christmas.timeInMillis
        kingdomDate.add(android.icu.util.Calendar.WEEK_OF_YEAR, -8);
        kingdomDate.firstDayOfWeek = android.icu.util.Calendar.SUNDAY
        kingdomDate.timeZone = tz
        kingdomDate.timeInMillis = findPreviousSunday(kingdomDate).timeInMillis

        val epiphanyDate = android.icu.util.Calendar.getInstance(tz);
        epiphanyDate.timeInMillis = christmas.timeInMillis
        epiphanyDate.add(android.icu.util.Calendar.WEEK_OF_YEAR, -2 );
        epiphanyDate.firstDayOfWeek = android.icu.util.Calendar.SUNDAY
        epiphanyDate.timeZone = tz
        epiphanyDate.timeInMillis = findPreviousSunday(epiphanyDate).timeInMillis

        val beforeLentDate = android.icu.util.Calendar.getInstance(tz);
        beforeLentDate.timeInMillis = epiphanyDate.timeInMillis
        beforeLentDate.add(android.icu.util.Calendar.WEEK_OF_YEAR, +2 );
        beforeLentDate.firstDayOfWeek = android.icu.util.Calendar.SUNDAY
        beforeLentDate.timeZone = tz
        beforeLentDate.timeInMillis = findPreviousSunday(beforeLentDate).timeInMillis

        val lentDate = android.icu.util.Calendar.getInstance(tz);
        lentDate.timeInMillis = easter.timeInMillis
        lentDate.add(android.icu.util.Calendar.DAY_OF_YEAR, -40 );
        lentDate.firstDayOfWeek = android.icu.util.Calendar.SUNDAY
        lentDate.timeZone = tz
        lentDate.timeInMillis = findPreviousSunday(lentDate).timeInMillis

        val trinityDate = android.icu.util.Calendar.getInstance(tz);
        trinityDate.timeInMillis = easter.timeInMillis
        trinityDate.add(android.icu.util.Calendar.WEEK_OF_YEAR, +6 );
        trinityDate.firstDayOfWeek = android.icu.util.Calendar.SUNDAY
        trinityDate.timeZone = tz
        trinityDate.timeInMillis = findPreviousSunday(trinityDate).timeInMillis


        AppDebug.log(
            "TAG",
            "Todays Date:"+cal.get(android.icu.util.Calendar.YEAR).toString() +":" +
                    cal.get(android.icu.util.Calendar.MONTH).toString()+ ":" +
                    cal.get(android.icu.util.Calendar.DAY_OF_MONTH).toString())
        AppDebug.log(
            "TAG",
            "Advent Date:"+adventDate.get(android.icu.util.Calendar.YEAR).toString() +":" +
                    adventDate.get(android.icu.util.Calendar.MONTH).toString()+ ":" +
                    adventDate.get(android.icu.util.Calendar.DAY_OF_MONTH).toString())
        AppDebug.log(
            "TAG",
            "Kingdom Date:"+kingdomDate.get(android.icu.util.Calendar.YEAR).toString() +":" +
                    kingdomDate.get(android.icu.util.Calendar.MONTH).toString()+ ":" +
                    kingdomDate.get(android.icu.util.Calendar.DAY_OF_MONTH).toString())
        AppDebug.log(
            "TAG",
            "Christmas Date:"+christmas.get(android.icu.util.Calendar.YEAR).toString() +":" +
                    christmas.get(android.icu.util.Calendar.MONTH).toString()+ ":" +
                    christmas.get(android.icu.util.Calendar.DAY_OF_MONTH).toString())
        AppDebug.log(
            "TAG",
            "Ephiphany Date:"+epiphanyDate.get(android.icu.util.Calendar.YEAR).toString() +":" +
                    epiphanyDate.get(android.icu.util.Calendar.MONTH).toString()+ ":" +
                    epiphanyDate.get(android.icu.util.Calendar.DAY_OF_MONTH).toString())

        AppDebug.log(
            "LECTIONARY",
            "Lent Date:"+lentDate.get(android.icu.util.Calendar.YEAR).toString() +":" +
                    lentDate.get(android.icu.util.Calendar.MONTH).toString()+ ":" +
                    lentDate.get(android.icu.util.Calendar.DAY_OF_MONTH).toString())
        AppDebug.log(
            "LECTIONARY",
            "Easter Date:"+easter.get(android.icu.util.Calendar.YEAR).toString() +":" +
                    easter.get(android.icu.util.Calendar.MONTH).toString()+ ":" +
                    easter.get(android.icu.util.Calendar.DAY_OF_MONTH).toString())

        if (cal.compareTo(easter) > 0) {
            val weeks = cal.timeInMillis - easter.timeInMillis
            val newCal = android.icu.util.Calendar.getInstance()
            newCal.timeInMillis = weeks;
            newCal.timeZone = tz
            val weeksSinceEaster = newCal[android.icu.util.Calendar.WEEK_OF_YEAR]
            AppDebug.log(
                "TAG",
                "(Lectionary) (Initial) Season:$season Week:$weekOfSeason Day:$dayOfWeek Week of Year:$weeksSinceEaster"
            )
            //dayOfWeek = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

            // weeksSinceEaster = weeksSinceEaster / (24 * 60 * 60 * 1000);

            if (weeksSinceEaster <= 6) {
                season = "EASTER"
                weekOfSeason = weeksSinceEaster

            } else {
                season = "TRINITY"
                weekOfSeason = weeksSinceEaster - 6
            }
        }

        if (cal.equals(kingdomDate) or (cal.after(kingdomDate) and cal.before(adventDate))) {
                season = "KINGDOM"
                //val long_kingdomWeeks = cal.timeInMillis - kingdomDate.timeInMillis
                val kingdom_week = (cal.get(android.icu.util.Calendar.WEEK_OF_YEAR) - kingdomDate.get(android.icu.util.Calendar.WEEK_OF_YEAR)) +1
                //val cal_weeksInKingdom= java.util.Calendar.Builder().setInstant(long_kingdomWeeks).build()
                //weekOfSeason = cal_weeksInKingdom.get(java.util.Calendar.WEEK_OF_YEAR);
                weekOfSeason = kingdom_week

                AppDebug.log("TAG","Date is in Kingdom. Week: "+weekOfSeason+" MEthod 2: "+kingdom_week)
        }
        if (cal.equals(adventDate) or cal.after(adventDate)) {
                season = "ADVENT"
                val advent_week = (cal.get(android.icu.util.Calendar.WEEK_OF_YEAR) - adventDate.get(android.icu.util.Calendar.WEEK_OF_YEAR)) +1

                //val long_adventWeeks = cal.timeInMillis - adventDate.timeInMillis
                //val cal_weeksInAdvent= java.util.Calendar.Builder().setInstant(long_adventWeeks).build()
                //weekOfSeason = cal_weeksInAdvent.get(java.util.Calendar.WEEK_OF_YEAR);

                weekOfSeason = advent_week;

                AppDebug.log("TAG","Date is in Advent. Week: "+weekOfSeason)
        }
        if (cal.equals(christmas) or (cal.before(epiphanyDate)) and cal.after(christmas)) {
            AppDebug.log("TAG", "After Christmas (Likely Nativity")

            season = "NATIVITY"

            val nativity_week = (cal.get(android.icu.util.Calendar.WEEK_OF_YEAR) - christmas.get(android.icu.util.Calendar.WEEK_OF_YEAR)) +1

           // weekOfSeason = cal[java.util.Calendar.WEEK_OF_YEAR]
            weekOfSeason = nativity_week

        }

        if (cal.equals(epiphanyDate) or (cal.after(epiphanyDate) and cal.before(beforeLentDate))) {
            season = "EPIPHANY"

            val epiphany_week = (cal.get(android.icu.util.Calendar.WEEK_OF_YEAR) - epiphanyDate.get(android.icu.util.Calendar.WEEK_OF_YEAR)) +1

            //weekOfSeason = cal[java.util.Calendar.WEEK_OF_YEAR] - 2
            weekOfSeason=epiphany_week
        }

        if(cal.equals(beforeLentDate) or (cal.after(beforeLentDate) and cal.before(lentDate)))
        {
            season= "BEFORELENT";
            val beforelent_week = (cal.get(android.icu.util.Calendar.WEEK_OF_YEAR) - lentDate.get(android.icu.util.Calendar.WEEK_OF_YEAR)) +1
            weekOfSeason=beforelent_week;
        }

        if(cal.equals(lentDate) or (cal.after(lentDate) and cal.before(easter)))
        {
            season= "LENT";
            val lent_week = (cal.get(android.icu.util.Calendar.WEEK_OF_YEAR) - lentDate.get(android.icu.util.Calendar.WEEK_OF_YEAR)) +1
            weekOfSeason=lent_week;
        }

        if(cal.equals(easter) or (cal.after(easter) and cal.before(trinityDate)))
        {
            season= "EASTER";
            val easter_week = (cal.get(android.icu.util.Calendar.WEEK_OF_YEAR) - lentDate.get(android.icu.util.Calendar.WEEK_OF_YEAR)) +1
            weekOfSeason=easter_week;
        }

        if(cal.equals(trinityDate) or (cal.after(trinityDate) and cal.before(kingdomDate)))
        {
            season= "TRINITY";
            val trinity_week = (cal.get(android.icu.util.Calendar.WEEK_OF_YEAR) - lentDate.get(android.icu.util.Calendar.WEEK_OF_YEAR)) +1
            weekOfSeason=trinity_week;
        }

        return arrayOf(season, weekOfSeason.toString())
    }


}