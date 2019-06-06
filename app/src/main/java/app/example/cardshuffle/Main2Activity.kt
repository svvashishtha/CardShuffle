package app.example.cardshuffle

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {
    val TAG = "MainActivity"

    //todo : Make the flip animation start on click event
//    Done : Use the card in a list so that scatter works after flip : done
//    todo: use the xml animation selector.

    private var scattered: Boolean = false
    private var flipping: Boolean = false
    var cardList = CustomQueue<View>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        addCardsToList()
        setCardBackGround()
        scatter.setOnClickListener { scatterCards() }
        collect_cards.setOnClickListener { collectCards() }
        flip_cards.setOnClickListener { flipCards() }
    }

    private fun flipCards() {
        if (!scattered) {
            if (!flipping) {//take card out of stack
                flipping = true
                cardList[2].animate()
                        .rotation(45f)
                        .translationX(resources.getDimension(R.dimen.translationX))
                        .translationYBy(3 * resources.getDimension(R.dimen.translationY))
                        .scaleX(0.9f)
                        .scaleY(0.9f)
                        .setInterpolator(FastOutSlowInInterpolator())
                        .withEndAction {
                            //pull card on top of other cards
                            cardList[2].z = 2f
                            //push 2 cards below
                            cardList[1].z = -2f
                            cardList[0].z = -2f

                            //bring cardList[2] to it's new position
                            cardList[2].animate()
                                    .rotation(0f)
                                    .translationX(0f)
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .translationYBy(-5 * resources.getDimension(R.dimen.translationY))

                                    .setInterpolator(FastOutSlowInInterpolator())
                                    .withEndAction {
                                        //change the position of card in the list
                                        //now cardList[2] will become cardList[0],
                                        //cardList[0] will become cardList[1],
                                        //cardList[1] will become cardList[2]
                                        cardList.moveToFront(2)
                                        flipping = false
                                    }
                                    .start()
                            //slide cardList[0] to 1 pos below
                            cardList[0].animate()
                                    .translationYBy(resources.getDimension(R.dimen.translationY))
                                    .setInterpolator(OvershootInterpolator(3f))
                                    .start()
                            //slide cardList[1] to 1 pos below
                            cardList[1].animate()
                                    .translationYBy(resources.getDimension(R.dimen.translationY))
                                    .setInterpolator(OvershootInterpolator(3f))
                                    .start()
                        }
                        .start()
            }
        } else {
            Toast.makeText(this, "Collect cards before flipping", LENGTH_SHORT).show()
        }

    }

    private fun collectCards() {
        if (scattered) {
            cardList[1].animate()
                    .rotation(0f)
                    .translationXBy(-50f)
                    .setInterpolator(OvershootInterpolator(3f))
                    .start()
            cardList[0].animate()
                    .rotation(0f)
                    .translationXBy(resources.getDimension(R.dimen.translationX))
                    .translationYBy(resources.getDimension(R.dimen.translationY))
                    .setInterpolator(OvershootInterpolator(3f))
                    .start()
            cardList[2].animate()
                    .rotation(0f)
                    .translationXBy(-1 * resources.getDimension(R.dimen.translationX))
                    .translationYBy(-1 * resources.getDimension(R.dimen.translationY))
                    .setInterpolator(OvershootInterpolator(3f))
                    .start()
            scattered = false
        } else {
            Toast.makeText(this, "Collect", LENGTH_SHORT).show()
        }
    }

    private fun scatterCards() {
        if (!scattered) {
            cardList[0].animate()
                    .rotation(-30f)
                    .translationXBy(-1 * resources.getDimension(R.dimen.translationX))
                    .translationYBy(-1 * resources.getDimension(R.dimen.translationY))
                    .setInterpolator(OvershootInterpolator(3f))
                    .start()
            cardList[1].animate()
                    .translationXBy(50f)
                    .setInterpolator(OvershootInterpolator(3f))
                    .start()
            cardList[2].animate()
                    .rotation(30f)
                    .translationXBy(resources.getDimension(R.dimen.translationX))
                    .translationYBy(resources.getDimension(R.dimen.translationY))
                    .setInterpolator(OvershootInterpolator(3f))
                    .start()
            scattered = true
        } else {
            Toast.makeText(this, "Scatter", LENGTH_SHORT).show()
        }
    }

    private fun addCardsToList() {
        card1.tag = "card1"
        card2.tag = "card2"
        card3.tag = "card3"

        cardList.add(card3)
        cardList.add(card2)
        cardList.add(card1)


    }

    private fun setCardBackGround() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (cardList[2] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_3, null))
        } else {
            (cardList[2] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_3))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (cardList[1] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_2, null))
        } else {
            (cardList[1] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_2))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (cardList[0] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.light_grey, null))
        } else {
            (cardList[0] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.light_grey))
        }
    }
}
