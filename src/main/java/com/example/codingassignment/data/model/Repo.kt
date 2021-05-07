package com.example.codingassignment.data.model
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "items")
@Parcelize
data class Repo(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    val fullName: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    val url: String,

    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    val stars: Int,

    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count")
    val forks: Int,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val language: String?,

    @ColumnInfo(name = "watchers")
    @SerializedName("watchers")
    val watchers: Int,

    @ColumnInfo(name = "owner")
    @SerializedName("owner")
    @Ignore
    val owner: Owner,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createDate: String,

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    val updateDate: String,

    @ColumnInfo(name = "open_issues")
    @SerializedName("open_issues")
    val openIssues: Int

) : Parcelable
