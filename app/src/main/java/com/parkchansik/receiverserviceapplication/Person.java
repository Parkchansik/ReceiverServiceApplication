package com.parkchansik.receiverserviceapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dongja94 on 2015-10-07.
 */
public class Person implements Parcelable {
    String name;
    int age;
    String message;

    public Person() {

    }

    public Person(Parcel source) {
        name = source.readString();
        age = source.readInt();
        message = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(message);
    }

    public static Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
