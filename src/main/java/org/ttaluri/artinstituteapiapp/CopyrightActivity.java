package org.ttaluri.artinstituteapiapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CopyrightActivity extends AppCompatActivity {

    private TextView copyrightTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyright);

        ImageView artInstituteLogo = findViewById(R.id.artInstituteLogo);
        copyrightTextView = findViewById(R.id.copyrightTextView);


        artInstituteLogo.setOnClickListener(v -> {
            Intent intent = new Intent(CopyrightActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        setCopyrightText();
    }

    private void setCopyrightText() {
        // Text content
        String content = "Content provided by\n\nArt Institute of Chicago API\n\n";
        String apiLink = "https://api.artic.edu/docs/\n\n\n\n\n\n\n";
        String fontInfo = "Work Sans font, Public Domain, \nGPL, OLF:\n\n";
        String fontLink = "https://www.1001freefonts.com/worksans.font\n\n\n\n";

        SpannableString spannableString = new SpannableString(content + apiLink + fontInfo + fontLink );


        ClickableSpan apiClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openLink("https://api.artic.edu/docs/");
            }
        };
        spannableString.setSpan(apiClickableSpan, content.length(), content.length() + apiLink.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        ClickableSpan fontClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openLink("https://www.1001freefonts.com/worksans.font");
            }
        };
        spannableString.setSpan(fontClickableSpan, content.length() + apiLink.length() + fontInfo.length(), content.length() + apiLink.length() + fontInfo.length() + fontLink.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        copyrightTextView.setText(spannableString);
        copyrightTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
