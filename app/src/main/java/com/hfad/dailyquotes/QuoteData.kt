
package com.hfad.dailyquotes

import com.hfad.dailyquotes.dataClass.QuoteCategory
import com.hfad.dailyquotes.dataClass.Quotedataclass

fun getHappyQuotes() : List<Quotedataclass> {
    return arrayListOf(
        Quotedataclass(  0,"Happiness is not something ready-made. it comes from your actions.",
            "Dalai Lama", false, QuoteCategory.Happy.name
        ),
        Quotedataclass(  1,"Happiness is when what you think, what you say , and what you do are in harmony.",
            "Mahatma Gandhi",false,  QuoteCategory.Happy.name
        ),
        Quotedataclass(  2,"For every minutes you are angry you loose sixty seconds of happiness.",
            "Walder Emerson",false, QuoteCategory.Happy.name
        ),
        Quotedataclass(  3,"The purpose of our lives is to be happy.",
            "Dalai Lama",false, QuoteCategory.Happy.name
        )
    )
}

fun getEnglishQuotes(): List<Quotedataclass> {

    return arrayListOf(

        Quotedataclass(  0,"Curiosity is the very first of the inventions of a child's genius.",
            "Geoffrey Chaucer", false, QuoteCategory.English.name
        ),

        Quotedataclass(
            1,"A dream you don't chase remains a dream. A goal you don't chase set remains a wish. A chance you don't take remains a regret",
            "Lewis Carroll",false, QuoteCategory.English.name
        ),

        Quotedataclass(
            2,"It is not the mountain we conquer, but ourselves.",
            "Edmund Hillary",false, QuoteCategory.English.name
        ),
        Quotedataclass(
            3,"I am not afraid of tomorrow, for i have learned from yesterday.",
            "Jane Austen",false, QuoteCategory.English.name
        )
    )

}

fun getAngryQuotes(): List<Quotedataclass> {
    return arrayListOf(
        Quotedataclass(0, "Anger is never without a reason, but seldom with a good one.", "Benjamin Franklin", false,  QuoteCategory.Angry.name),
        Quotedataclass(1, "Never go to bed mad. Stay up and fight.", "Phyllis Diller", false, QuoteCategory.English.name),
        Quotedataclass(2, "For every minute you remain angry, you give up sixty seconds of peace of mind.", "Ralph Waldo Emerson", false, QuoteCategory.Angry.name),
        Quotedataclass(3, "Speak when you are angry and you will make the best speech you will ever regret.", "Ambrose Bierce", false, QuoteCategory.Angry.name)
    )
}

fun getFunnyQuotes(): List<Quotedataclass> {

    return arrayListOf(

        Quotedataclass(0, "I'm not arguing, I'm just explaining why I'm right.", "Unknown", false, QuoteCategory.Funny.name),
        Quotedataclass(1, "I used to think i was indecisive, but now I'm not so sure .", "Unknown", false, QuoteCategory.Funny.name),
        Quotedataclass(2, "I'm so clever that sometimes i don't understand a single.word of what i'm saying", "Oscar Wilde", false, QuoteCategory.Funny.name),
        Quotedataclass(3, "I always wanted to be somebody, but now i realize i should have been more specific.", "Lilly Tomlin", false, QuoteCategory.Funny.name)
    )
}

fun getSadQuotes(): List<Quotedataclass> {

    return arrayListOf(

        Quotedataclass(0, "The walls we build around us to keep sadness out also keeps out the joy.", "Jim Rohn", false, QuoteCategory.Sad.name),
        Quotedataclass(1, "Every man has his secret sorrows which the world knows not; and often times we call a man cold when he is only sad.", "Henry WadsWorth LongFellow", false, QuoteCategory.Sad.name),
        Quotedataclass(2, "Tears comes from the heart not from the brain.", "Leonardo da Vinci", false, QuoteCategory.Sad.name),
        Quotedataclass(3, "The good times of today are the sad thoughts of tomorrow.", "Bob Marley", false,  QuoteCategory.Sad.name)
    )
}

fun getTiredQuotes(): List<Quotedataclass> {

    return arrayListOf(

        Quotedataclass(0, "I'm so tired of feeling tired.", "Unknown", false, QuoteCategory.Tired.name),
        Quotedataclass(1, "Fatigue is the best pillow.", "Benjamin Franklin", false, QuoteCategory.Tired.name),
        Quotedataclass(2, "Tired minds don't plan well. sleep first, plan later.", "Walter Reisch", false, QuoteCategory.Tired.name),
        Quotedataclass(3, "The best bridge between despair and hope is a good sleep.", "E.Joseph Cossman", false, QuoteCategory.Tired.name)
    )
}

fun getSuccessQuotes(): List<Quotedataclass> {

    return arrayListOf(

        Quotedataclass(  0,"Success is stumbling from failure to failure with no loss of enthusiasm.",
            "Winston Churchill",false, QuoteCategory.Success.name
        ),


        Quotedataclass(
            1,"The only way to do great work is to love what you do. if you haven't found it yet, keep looking.",
            "Steve Jobs",false, QuoteCategory.Success.name
        ),

        Quotedataclass(
            2,"There are three ways to ultimate success: First, be kind. Second, be kind. Third, be kind.",
            "Henry James",false, QuoteCategory.Success.name
        ),

        Quotedataclass(
            3,"Don't be afraid to give up the good to go for the great.",
            "John D. Rockefeller",false, QuoteCategory.Success.name
        )
    )
}

fun getMotivationQuotes(): List<Quotedataclass> {

    return arrayListOf(

        Quotedataclass(  0,"I wasn't there to compete, I was there to win.",
            "Arnold Schwarzenegger",false, QuoteCategory.Motivation.name
        ),


        Quotedataclass(
            1,"Someone once told me growth and comfort do not coexist.",
            "Ginni Rometty", false, QuoteCategory.Motivation.name
        ),

        Quotedataclass(
            2,"It's not about how many times you fall that cunts. It's about how many times you get back up.",
            "Nelson Mandela",false, QuoteCategory.Motivation.name
        ),


        Quotedataclass(
            3,"The difference between ordinary and extraordinary is that little extra.",
            "Jimmy Johnson",false, QuoteCategory.Motivation.name
        )
    )
}

fun getLoveQuotes(): List<Quotedataclass> {
    return arrayListOf(
        Quotedataclass(
            0,"To love and to be loved is to feel the sun from both sides.",
            "David Viscott", false, QuoteCategory.Love.name
        ),

        Quotedataclass(
            1,"You may not always be together but you will never be apart. For the greatest thing you will ever learn is to love and be loved in return.",
            "Moulin Rouge",false, QuoteCategory.Love.name
        ),


        Quotedataclass(
            2,"Love is not about finding someone to complete you; it's about finding someone who accepts you completely.",
            "Roy croft",false, QuoteCategory.Love.name
        ),

        Quotedataclass(
            3,"A successful marriage requires falling in love many times, always with the same person.",
            "Mignon McLaughlin", false, QuoteCategory.Love.name
        )
    )
}

fun getPrePopulatedData(): List<Quotedataclass> {
    return arrayListOf(
        Quotedataclass( 0,"Life is what happens when you are busy making other plans",
            "John Lennon", false, QuoteCategory.Life.name),

        Quotedataclass( 1,"Twenty years from now, you will be more disappointed by the things that you didn't do than by the ones you did do.",
            "Mark Twain",false, QuoteCategory.Life.name
        ),


        Quotedataclass(  2,"The only person you are destined to become is the person you decide to be.",
            "Ralph Waldo",false, QuoteCategory.Life.name),


        Quotedataclass(  3,"The purpose of life is not to be happy., It is to be useful, to be honorable, to be compassionate, to have it make some difference that you have lived and lived well.",
            "Ralph Waldo",false, QuoteCategory.Life.name
        )

    )
}