package app.example.cardshuffle

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {
    val TAG = "MainActivity"

    //todo : Make the flip animation start on click event
//    t odo : Use the card in a list so that scatter works after flip : done
//    todo: use the xml animation selector.


    var cardList = CustomQueue<View>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        addCards()

        scatter.setOnClickListener { scatterCards() }
        collect_cards.setOnClickListener { collectCards() }
        flip_cards.setOnClickListener { flipCards() }

    }


    private fun flipCards() {
        var handler = Handler()
        handler.postDelayed({
            cardList.moveToFront(2) }, 500)
        cardList[2].animate()
                .rotation(45f)
                .translationX(resources.getDimension(R.dimen.translationX))
                .translationYBy(2 * resources.getDimension(R.dimen.translationYMax))
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setInterpolator(FastOutSlowInInterpolator())
                .setListener(object : AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                        Log.d(TAG, "animation repeating")
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        Log.d(TAG, "animation cancelled")
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        Log.d(TAG, "animation started")
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        cardList[2].animate()
                                .rotation(0f)
                                .translationX(0f)
                                .scaleX(1f)
                                .scaleY(1f)
                                .translationY(-2 * resources.getDimension(R.dimen.translationY))
                                .translationZ(5f)
                                .setInterpolator(FastOutSlowInInterpolator())
                                .start()
                        cardList[0].animate()
                                .translationY(resources.getDimension(R.dimen.translationY))
                                .setInterpolator(OvershootInterpolator(3f))
                                .start()
                        cardList[1].animate().translationY(resources.getDimension(R.dimen.translationY))
                                .setInterpolator(OvershootInterpolator(3f))
                                .start()
                    }
                })
                .start()

    }

    private fun collectCards() {
        cardList[1].animate()
                .rotation(0f)
                .translationX(0f)
                .setInterpolator(OvershootInterpolator(3f))
                .start()
        cardList[0].animate()
                .rotation(0f)
                .translationX(0f)
                .translationY(0f)
                .setInterpolator(OvershootInterpolator(3f))
                .start()
        cardList[2].animate()
                .rotation(0f)
                .translationX(0f)
                .translationY(0f)
                .setInterpolator(OvershootInterpolator(3f))
                .start()
    }

    private fun scatterCards() {
        cardList[0].animate()
                .rotation(-30f)
                .translationX(-1 * resources.getDimension(R.dimen.translationX))
                .translationY(-1 * resources.getDimension(R.dimen.translationY))
                .setInterpolator(OvershootInterpolator(3f))
                .start()
        cardList[1].animate()
                .translationX(50f)
                .setInterpolator(OvershootInterpolator(3f))
                .start()
        cardList[2].animate()
                .rotation(30f)
                .translationX(resources.getDimension(R.dimen.translationX))
                .translationY(resources.getDimension(R.dimen.translationY))
                .setInterpolator(OvershootInterpolator(3f))
                .start()
    }

    private fun addCards() {
        card1.tag = "card1"
        card2.tag = "card2"
        card3.tag = "card3"

        cardList.add(card3)
        cardList.add(card2)
        cardList.add(card1)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (cardList[2] as? CardView)?.setCardBackgroundColor(resources.getColor(R.color.card_3, null))
        } else {
//            card3.setBackgroundColor(resources.getColor(R.color.card_3))
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
