package by.bsuir.proslau.goparty.logic.profile

import by.bsuir.proslau.goparty.ui.all_events.ProfileForEvents

data class ProfileImpl(override val avatar : String, override val username : String) :
    ProfileForEvents {
    
}