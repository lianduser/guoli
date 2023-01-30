package com.example.shoppingmall2.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppingmall2.R;

public class BookContentDescriptionFragment extends Fragment {
    private TextView webView_content_description;

    public static BookContentDescriptionFragment newInstance(String Description){
        BookContentDescriptionFragment bookContentDescriptionFragment = new BookContentDescriptionFragment();
        Bundle arguments = new Bundle();
        arguments.putString("content",Description);
        bookContentDescriptionFragment.setArguments(arguments);
        return bookContentDescriptionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_content_description,null);
        webView_content_description = view.findViewById(R.id.webView_content_description);
        String content = getArguments().getString("content");
        webView_content_description.setText(content);
        return view;

    }
}
