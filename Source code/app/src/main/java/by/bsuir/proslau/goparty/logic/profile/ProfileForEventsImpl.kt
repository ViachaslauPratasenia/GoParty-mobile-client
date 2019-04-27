package by.bsuir.proslau.goparty.logic.profile

import by.bsuir.proslau.goparty.ui.all_events.ProfileForEvents
import java.util.*

data class ProfileForEventsImpl(override val id: UUID, override val avatar : String, override val username : String) :
    ProfileForEvents {
    companion object {
        fun initProfiles() : List<ProfileForEvents> {
            val list : ArrayList<ProfileForEvents> = ArrayList()
            list.add(ProfileForEventsImpl(
                UUID.fromString("2dfd8c9e-f32d-466e-a386-1e9d751c4d87"), "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Ninja-2-512.png",
                "Proslau"))
            list.add(ProfileForEventsImpl(UUID.fromString("2dfd824e-f32d-466e-a386-1e9d751c4d87"), "https://cdn3.iconfinder.com/data/icons/avatars-15/64/-26-512.png",
                "Vitaliy"))
            list.add(ProfileForEventsImpl(UUID.fromString("2dfd8c9e-f322-466e-a386-1e9d751c4d87"), "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Modern_Business_Woman-256.png",
                "Veronika"))
            list.add(ProfileForEventsImpl(UUID.fromString("2dfd8c9e-f32d-466e-a386-1e12351c4d87"), "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Modern_Woman-128.png",
                "Katya"))
            list.add(ProfileForEventsImpl(UUID.randomUUID(), "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Carpenter_Man-256.png",
                "Valera"))
            return list
        }

        fun getProfileById(id : UUID) : ProfileForEvents? {
            val list = initProfiles()
            list.forEach {
                if(it.id == id) return it
            }
            return null
        }
    }
}