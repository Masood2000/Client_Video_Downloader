package com.example.apiproject.domain.models




data class InterestModel(
    val interestName: String,
    //val languageFlag: Int = 0,
    var isSelected:Boolean = false
) {
    companion object {

        var interestModelList: MutableList<InterestModel> = mutableListOf(
            InterestModel("Travel vlogs",false),
            InterestModel("Food vlogs",false),
            InterestModel("Science",false),
            InterestModel("Comedy",false),
            InterestModel("Fitness",false),
            InterestModel("How to Style",false),
            InterestModel("Gaming",false),
            InterestModel("Movie",false),
            InterestModel("Fashion",false),
            InterestModel("Sports",false),
            InterestModel("Wildlife",false),
            InterestModel("Workout",false),
            InterestModel("Book Review",false),
            InterestModel("How to?",false),

        )
    }
}