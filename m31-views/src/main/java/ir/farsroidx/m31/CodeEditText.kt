package ir.farsroidx.m31

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class CodeEditText : AppCompatEditText, TextWatcher {

    constructor(context: Context)
        : this(context, null)

    constructor(context: Context, attrs: AttributeSet?)
        : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
        : super(context, attrs, defStyleAttr) { initialize(context, attrs, defStyleAttr) }

    private fun initialize(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        addTextChangedListener(this)
    }

    private fun highlightSyntax(editable: Editable) {

        val keywords = arrayOf(
            " as ",
            " as? ",
            " break ",
            " class ",
            " continue ",
            " do ",
            " else ",
            " false ",
            " for ",
            " fun ",
            " if ",
            " in ",
            " !in ",
            " interface ",
            " is ",
            " !is ",
            " null ",
            " object ",
            " package ",
            " return ",
            " super ",
            " this ",
            " throw ",
            " true ",
            " try ",
            " typealias ",
            " val ",
            " var ",
            " when ",
            " while ",
            " by ",
            " catch ",
            " constructor ",
            " delegate ",
            " dynamic ",
            " field ",
            " file ",
            " finally ",
            " get ",
            " import ",
            " init ",
            " param ",
            " property ",
            " receiver ",
            " set ",
            " setparam ",
            " where ",
            " actual ",
            " abstract ",
            " annotation ",
            " companion ",
            " const ",
            " crossinline ",
            " data ",
            " enum ",
            " expect ",
            " external ",
            " final ",
            " infix ",
            " inline ",
            " inner ",
            " internal ",
            " lateinit ",
            " noinline ",
            " open ",
            " operator ",
            " out ",
            " override ",
            " private ",
            " protected ",
            " public ",
            " package ",
            " reified ",
            " sealed ",
            " suspend ",
            " tailrec ",
            " vararg ",
            " field ",
            " it "
        )

        val keywordColor = Color.parseColor("#FF6D00")
        val stringColor  = Color.parseColor("#00C853")
        val commentColor = Color.parseColor("#757575")

        // Clear previous spans
        val spans = editable.getSpans(0, editable.length, ForegroundColorSpan::class.java)

        for (span in spans) {
            editable.removeSpan(span)
        }

        // Highlight keywords
        for (keyword in keywords) {

            var index = editable.toString().indexOf(keyword)

            while (index >= 0) {

                editable.setSpan(ForegroundColorSpan(keywordColor),
                    index, index + keyword.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                index = editable.toString().indexOf(keyword, index + keyword.length)
            }
        }

        // Highlight strings
        val regexString = "\"(.*?)\"".toRegex()
        val matchesString = regexString.findAll(editable.toString())
        for (match in matchesString) {
            editable.setSpan(
                ForegroundColorSpan(stringColor),
                match.range.first, match.range.last + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        // Highlight comments
        val regexComment = "//.*".toRegex()
        val matchesComment = regexComment.findAll(editable.toString())

        for (match in matchesComment) {

            editable.setSpan(
                ForegroundColorSpan(commentColor),
                match.range.first, match.range.last + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(editable: Editable) {
        highlightSyntax(editable)
    }
}