package com.android.daily.ui.home.jetpack

import com.android.jetpack.workmanager.WorkManagerActivity

/**
 * author : hefeng
 * date : 2022/7/25
 * description :
 */
object JetpackDataUtils {
    fun getJetpackListData(): List<JetpackInfo> {
        val list = mutableListOf<JetpackInfo>()

        list.add(JetpackInfo("WorkManager", "https://git.poker/fenggit/android-daily-resource/blob/master/jetpack_workmanager.5dj8b4hc5o00.jpg?raw=true", WorkManagerActivity::class.java))
        list.add(JetpackInfo("Hilt", "https://git.poker/fenggit/android-daily-resource/blob/master/jetpack_hilt.783con5nixw0.jpg?raw=true", WorkManagerActivity::class.java))
        list.add(JetpackInfo("DataStore", "https://git.poker/fenggit/android-daily-resource/blob/master/jetpack_data_store.3s0ttc0ky9q0.jpg?raw=true", WorkManagerActivity::class.java))
        list.add(JetpackInfo("LiveData", "https://git.poker/fenggit/android-daily-resource/blob/master/jetpack_livedata.2y40xhksm540.jpg?raw=true", WorkManagerActivity::class.java))
        list.add(JetpackInfo("ViewModel", "https://git.poker/fenggit/android-daily-resource/blob/master/jetpack_view_model.7la8sja64240.jpg?raw=true", WorkManagerActivity::class.java))
        list.add(JetpackInfo("Startup", "https://git.poker/fenggit/android-daily-resource/blob/master/jetpack_startup.65vpmf75aog0.jpg?raw=true", WorkManagerActivity::class.java))
        list.add(JetpackInfo("WindowManager", "https://git.poker/fenggit/android-daily-resource/blob/master/jetpack_window_manager.4ca17dg1ys4.jpg?raw=true", WorkManagerActivity::class.java))
        list.add(JetpackInfo("Lifecycle", "https://git.poker/fenggit/android-daily-resource/blob/master/jetpack_lifecycle.b1pnbv8hp8g.jpg?raw=true", WorkManagerActivity::class.java))

        return list
    }
}