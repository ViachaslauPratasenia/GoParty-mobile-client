package by.bsuir.proslau.goparty.logic.profile

import by.bsuir.proslau.goparty.ui.all_events.ProfileForEvents

data class ProfileForEventsImpl(override val id: Long, override val avatar : String, override val username : String) :
    ProfileForEvents {
    companion object {
        fun initProfiles() : List<ProfileForEvents> {
            val list : ArrayList<ProfileForEvents> = ArrayList()
            list.add(ProfileForEventsImpl(1, "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Ninja-2-512.png",
                "Proslau"))
            list.add(ProfileForEventsImpl(2, "https://cdn3.iconfinder.com/data/icons/avatars-15/64/-26-512.png",
                "Vitaliy"))
            list.add(ProfileForEventsImpl(3, "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Modern_Business_Woman-256.png",
                "Veronika"))
            list.add(ProfileForEventsImpl(4, "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Modern_Woman-128.png",
                "Katya"))
            list.add(ProfileForEventsImpl(5, "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Carpenter_Man-256.png",
                "Valera"))
            return list
        }

        fun getProfileById(id : Long) : ProfileForEvents? {
            val list = initProfiles()
            list.forEach {
                if(it.id == id) return it
            }
            return null
        }
    }
}