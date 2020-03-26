package com.proje.mobiluygulama.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;
import com.proje.mobiluygulama.R;
import com.proje.mobiluygulama.intro.OnboardingAdapter;
import com.proje.mobiluygulama.intro.OnboardingItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnBoardingItems();

        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onboardingViewPager.getCurrentItem() < onboardingAdapter.getItemCount()) {
                    if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                        onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                }
            }
        });
    }

    private void  setupOnBoardingItems() {

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemGelir = new OnboardingItem();
        itemGelir.setTitle("Gelir ve Gider Takibi");
        itemGelir.setDescription("Giderleri kolayca girin, ayağınızı yorganına göre uzatın :)");
        itemGelir.setImage(R.drawable.para);

        OnboardingItem itemGider = new OnboardingItem();
        itemGider.setTitle("Excel");
        itemGider.setDescription("Verilerinizi raporlaştıralım, işinizi kolaylaştıralım");
        itemGider.setImage(R.drawable.excel);

        OnboardingItem itemKurlar = new OnboardingItem();
        itemKurlar.setTitle("Anlık Kur Takibi");
        itemKurlar.setDescription("Kur takibi yapabilirsiniz");
        itemKurlar.setImage(R.drawable.kur);

        OnboardingItem itemNot = new OnboardingItem();
        itemNot.setTitle("Not Ekleyin");
        itemNot.setDescription("Faturaları önemli finansal işlemleri not alın, kafanız rahat olsun.");
        itemNot.setImage(R.drawable.not);

        onboardingItems.add(itemGelir);
        onboardingItems.add(itemGider);
        onboardingItems.add(itemKurlar);
        onboardingItems.add(itemNot);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);

    }

    private void setupOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if(i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if(index == onboardingAdapter.getItemCount() - 1) {
            buttonOnboardingAction.setText("Başla");
        } else {
            buttonOnboardingAction.setText("Geç");
        }
    }
}
