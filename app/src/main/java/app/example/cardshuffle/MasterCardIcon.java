package app.example.cardshuffle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MasterCardIcon extends View {
    RectF rect;
    Paint circle1, circle2;
    float x1, y1;
    float x2;
    Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);

    public MasterCardIcon(Context context) {
        super(context);
        init();
    }

    public MasterCardIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MasterCardIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        circle1 = new Paint();
        circle1.setStyle(Paint.Style.FILL);
        circle1.setAntiAlias(true);
        circle1.setColor(getResources().getColor(R.color.circle1));
        circle1.setAlpha(0x90);

        circle2 = new Paint();
        circle2.setStyle(Paint.Style.FILL);
        circle2.setAntiAlias(true);
        circle2.setColor(getResources().getColor(R.color.circle2));
        circle2.setAlpha(0x90);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawCircle(x1, y1, x1, circle1);
        canvas.drawCircle(x2, y1, x1, circle2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        x1 = (float) ((getMeasuredWidth() * 0.6) / 2);
        y1 = (float) ((getMeasuredWidth() * 0.6) / 2);
        x2 = getMeasuredWidth() - (x1);
        setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredWidth() * 0.6));

    }
}
