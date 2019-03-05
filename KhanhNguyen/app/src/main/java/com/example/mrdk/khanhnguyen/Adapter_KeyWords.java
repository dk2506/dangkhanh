package com.example.mrdk.khanhnguyen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Adapter_KeyWords extends RecyclerView.Adapter<Adapter_KeyWords.Holder_KeyWords> {
    ArrayList<Model_KeyWord> lstItem;
    Context mContext;

    String [] myColor =  new String[]{"#473C8B","#A0522D","#528B8B","#2F4F4F","#006400","#8B6914","#CD5555"};
    int oldColor= 0;
    public Adapter_KeyWords(Context mContext, ArrayList<Model_KeyWord> lstItem) {
        this.mContext = mContext;
        this.lstItem = lstItem;
    }

    @Override
    public Holder_KeyWords onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_keywords, parent, false);
        Holder_KeyWords vh = new Holder_KeyWords(itemView);
        return vh;
    }


    @Override
    public void onBindViewHolder(Holder_KeyWords holder, int position) {
        Model_KeyWord item = lstItem.get(position);

        if(!item.getName().equals(""))
            holder.txtKeyWord.setText(SplitWord(item.getName()));


        int newColor = getColor();

        while (newColor==oldColor)
            newColor=getColor();

        holder.cardView.setCardBackgroundColor(newColor);
        oldColor=newColor;
    }



    @Override
    public int getItemCount() {
        return lstItem.size();
    }

    public class Holder_KeyWords extends RecyclerView.ViewHolder {
        TextView txtKeyWord;
        CardView cardView;
        public Holder_KeyWords(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cvKeyWords);
            txtKeyWord = itemView.findViewById(R.id.txtKeyWord);
        }
    }

    public int getColor() {
        String color = "";

        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(myColor.length);

        color = myColor[randomNumber];
        @SuppressLint("Range") int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

    String SplitWord(String str){
        if (str == null || str.isEmpty())
            return null;
        String rs="";
        String[] words = str.split("\\s+");

        // Case 2 words
        if( words.length ==2){
            List< String > lstItem = Arrays.asList(words);

            return lstItem.get(0).trim() +"\n" + lstItem.get(1).trim();
        }

        //Case > 2 words
        if(words.length>2) {
            int strLenght = str.length();
            int midStr = strLenght / 2;

            String part1 = str.substring(0, midStr);
            String part2 = str.substring(midStr, str.length());

            List<String> words1 = Arrays.asList(part1.split("\\s+"));

            List<String> words2 = Arrays.asList(part2.split("\\s+"));

            //Case two parts have same length
            if(part1.endsWith(" ") || part2.startsWith(" "))
                return part1 +"\n" + part2;

            if (words1.get(words1.size() - 1).length() < words2.get(0).length()) {
                for (int i = 0; i < words1.size(); i++) {
                    if (i == words1.size() - 1)
                        rs = rs + "\n" + words1.get(i);
                    else
                        rs = rs + words1.get(i) + " ";
                }
                rs = rs  + part2;
            } else {
                for (int i = 0; i < words2.size(); i++) {
                    if (i == 0)
                        rs = words2.get(i) +"\n" ;
                    else
                        rs = rs + words2.get(i) + " ";
                }
                rs = part1.trim() + rs;
            }
        }//Case a word
        else
            rs = str;
        return rs;
    }
}
