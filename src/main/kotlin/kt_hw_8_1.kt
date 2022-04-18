package ru.netology

data class Note(
    val noteId: Int,
    var title: String,
    var text: String,
    var isDeleted: Boolean = false,
    val commentList: MutableList<Comment> = mutableListOf<Comment>()
)

data class Comment(
    val commentId: Int, var isDeleted: Boolean = false, val noteId: Int, var message: String
)

class ItemAlreadyDeleted(message: String) : RuntimeException(message)

class NoteService {
    private var noteId = 0
    private var commentId = 0
    private var noteList = mutableListOf<Note>()

    init {

        var retInd: Int

        for (indNote in 1..5) {
            retInd = this.add("Note $indNote", "Text $indNote")
            for (indComment in 1..5) {
                this.createComment(retInd, "Comment $indComment to Note $retInd")
            }
        }
        this.delete(3)
    }

    fun add(title: String, text: String): Int {
        noteId++
        noteList += Note(noteId = noteId, title = title, text = text)
        return noteId
    }

    fun createComment(noteId: Int, message: String): Int {
        commentId++
        noteList[noteId - 1].commentList.add(
            Comment(
                commentId,
                noteId = noteId, message = message
            )
        )
        return commentId
    }

    fun delete(noteId: Int): Boolean {
        if (noteList[noteId].isDeleted) throw ItemAlreadyDeleted("Note $noteId is deleted.")
        noteList[noteId].isDeleted = true
        return true
    }

    fun deleteComment(commentId: Int): Boolean {
        for (value in noteList)
            for (item in value.commentList) {
                if (item.commentId == commentId) {
                    item.isDeleted = true
                    return true
                }
            }
        return false
    }

    fun edit(noteId: Int, title: String, text: String): Boolean {
        if (noteList[noteId].isDeleted)
            return false
        noteList[noteId].title = title
        noteList[noteId].text = text
        return true
    }

    fun editComment(commentId: Int, message: String): Boolean {
        for (value in noteList)
            for (item in value.commentList) {
                if (item.commentId == commentId) {
                    if (item.isDeleted) return false
                    item.message = message
                    return true
                }
            }
        return false
    }

    fun get(offset: Int, count: Int, ascSort: Boolean, vararg noteIdsArray: Int): List<Note> {
        var resultList: List<Note> = listOf()

        if (count != 0 && offset < noteList.size) {
            if (offset + count - 1 < noteList.size) {
                resultList = noteList.subList(offset, offset + count)
            } else {
                resultList = noteList.subList(offset, noteList.size)
            }
        }

        if (!noteIdsArray.isEmpty()) {
            noteIdsArray.forEach {
                resultList += getById(it)
            }
        }

        return if (ascSort) resultList.sortedBy { it.noteId }
        else resultList.sortedByDescending { it.noteId }
    }

    fun getById(noteId: Int): Note {
        return noteList[noteId - 1].copy()
    }

    fun getComments(noteId: Int, offset: Int, count: Int, ascSort: Boolean): List<Comment> {
        var resultList: List<Comment> = listOf()

        if (count != 0 && offset < noteList[noteId].commentList.size) {
            if (offset + count - 1 < noteList[noteId].commentList.size) {
                resultList = noteList[noteId].commentList.subList(offset, offset + count)
            } else {
                resultList = noteList[noteId].commentList.subList(
                    offset,
                    noteList[noteId].commentList.size
                )
            }
        }

        return if (ascSort) resultList.sortedBy { it.commentId } else resultList.sortedByDescending { it.commentId }
    }

    fun restoreComment(commentId: Int): Boolean {
        for (value in noteList)
            for (item in value.commentList) {
                if (item.commentId == commentId) {
                    item.isDeleted = false
                    return true
                }
            }
        return false
    }
}


fun main() {
}