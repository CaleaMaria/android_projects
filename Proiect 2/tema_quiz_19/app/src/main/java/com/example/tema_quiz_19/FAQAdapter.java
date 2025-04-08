package com.example.tema_quiz_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class FAQAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> questionList;
    private HashMap<String, List<String>> answerMap;

    public FAQAdapter(Context context, List<String> questionList, HashMap<String, List<String>> answerMap) {
        this.context = context;
        this.questionList = questionList;
        this.answerMap = answerMap;
    }

    @Override
    public int getGroupCount() {
        return questionList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return answerMap.get(questionList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return questionList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return answerMap.get(questionList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String question = (String) getGroup(groupPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(question);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String answer = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(answer);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
