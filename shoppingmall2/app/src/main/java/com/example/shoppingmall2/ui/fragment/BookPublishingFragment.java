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
import com.example.shoppingmall2.vo.BookInfo;

public class BookPublishingFragment extends Fragment {

    private TextView textView5_bookname_content,textView5_isbn_content,textView5_author_content
            ,textView5_publishing_content,textView5_publishingTime_content,
            textView5_revision_content,textView5_impression_content,
            textView5_pages_content,textView5_numberOfWords_content,
            textView5_folio_content,textView5_pager_content,textView5_packing_content;

    public static BookPublishingFragment newInstance(BookInfo bookInfo){
        BookPublishingFragment bookPublishingFragment = new BookPublishingFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("bookInfo",bookInfo);
        bookPublishingFragment.setArguments(arguments);
        return bookPublishingFragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publishing_info,null);
        textView5_bookname_content = (TextView) view.findViewById(R.id.textView5_bookname_content);
        textView5_isbn_content = (TextView) view.findViewById(R.id.textView5_isbn_content);
        textView5_author_content = (TextView) view.findViewById(R.id.textView5_author_content);
        textView5_publishing_content = (TextView) view.findViewById(R.id.textView5_publishing_content);
        textView5_publishingTime_content = (TextView) view.findViewById(R.id.textView5_publishingTime_content);
        textView5_revision_content = (TextView) view.findViewById(R.id.textView5_revision_content);
        textView5_impression_content = (TextView) view.findViewById(R.id.textView5_impression_content);
        textView5_pages_content = (TextView) view.findViewById(R.id.textView5_pages_content);
        textView5_numberOfWords_content = (TextView) view.findViewById(R.id.textView5_numberOfWords_content);
        textView5_folio_content = (TextView) view.findViewById(R.id.textView5_folio_content);
        textView5_pager_content = (TextView) view.findViewById(R.id.textView5_pager_content);
        textView5_packing_content = (TextView) view.findViewById(R.id.textView5_packing_content);

        initData();
        return view;
    }

    private void initData() {
        BookInfo bookInfo = (BookInfo) getArguments().getSerializable("bookInfo");
        if(bookInfo!=null) {
            textView5_bookname_content.setText(bookInfo.getBookName());
            textView5_isbn_content.setText(bookInfo.getISBN());
            textView5_author_content.setText(bookInfo.getAuthor());
            textView5_publishing_content.setText(bookInfo.getPublishingCompany());
            textView5_publishingTime_content.setText(bookInfo.getPublishingTime());
            textView5_revision_content.setText(String.valueOf(bookInfo.getRevision()));
            textView5_impression_content.setText(String.valueOf(bookInfo.getImpression()));
            textView5_pages_content.setText(String.valueOf(bookInfo.getPages()));
            textView5_numberOfWords_content.setText(String.valueOf(bookInfo.getNumberOfWords()));
            textView5_folio_content.setText(bookInfo.getFolio());
            textView5_pager_content.setText(bookInfo.getPaper());
            textView5_packing_content.setText(bookInfo.getPacking());
        }
    }
}
