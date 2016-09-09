package vaycent.vaycentproject.DemoPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 16/9/9.
 */
public class TextViewDemo extends AppCompatActivity {

    private TextView d_color_linktx;
    private TextView spannable_tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview_demo);

        d_color_linktx=(TextView)findViewById(R.id.d_color_linktx);
        d_color_linktx.setMovementMethod(LinkMovementMethod.getInstance());
        Spanned text = Html.fromHtml("<a href=\"http://vaycent.github.io\">Vaycent's website here</a>");
        d_color_linktx.setText(text);


        spannable_tx=(TextView)findViewById(R.id.spannable_tx);
        String spannableTestStr = "You have run 5963 steps";
        int start = spannableTestStr.indexOf('5');
        int end = spannableTestStr.length();
        Spannable textSpan = new SpannableStringBuilder(spannableTestStr);
        textSpan.setSpan(new AbsoluteSizeSpan(40), 0, start, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(70), start, end - 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(40), end - 5, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        spannable_tx.setText(textSpan);

    }




}
