package ru.netology

import org.junit.Assert.*
import org.junit.Test

class NoteServiceTest {

    @org.junit.Test
    fun add() {
        val service = NoteService()
        val ret = service.add("Note 6", "text 6")
        assertEquals(ret, 6)
    }

    @org.junit.Test
    fun createComment() {
        val service = NoteService()
        val ret = service.createComment(1, "Comment 6 to Note 1")
        assertEquals(ret, 26)
    }

    @org.junit.Test
    fun delete() {
        val service = NoteService()
        val ret = service.delete(2)
        assertTrue(ret)
    }

    @Test(expected = ItemAlreadyDeleted::class)
    fun delete_already_deleted() {
        val service = NoteService()
        val ret = service.delete(3)
    }

    @org.junit.Test
    fun deleteComment() {
        val service = NoteService()
        val ret = service.deleteComment(15)
        assertTrue(ret)
    }

    @org.junit.Test
    fun delete_unexisting_comment() {
        val service = NoteService()
        val ret = service.deleteComment(100500)
        assertFalse(ret)
    }

    @org.junit.Test
    fun edit() {
        val service = NoteService()
        val ret = service.edit(1, "New Title", "New Text")
        assertTrue(ret)
    }


    @org.junit.Test
    fun editComment() {
        val service = NoteService()
        val ret = service.editComment(3, "New Message")
        assertTrue(ret)
    }


    @org.junit.Test
    fun get() {
        val service = NoteService()
        val ret = service.get(1, 1, true)
        assertTrue(!ret.isEmpty() && ret.size == 1)
    }

    @org.junit.Test
    fun get_portion() {
        val service = NoteService()
        val ret = service.get(1, 3, true)
        assertEquals(ret.size, 3)
    }

    @org.junit.Test
    fun get_with_out_of_bound_offset() {
        val service = NoteService()
        val ret = service.get(15, 3, true)
        assertTrue(ret.isEmpty())
    }

    @org.junit.Test
    fun get_with_out_of_bound_count() {
        val service = NoteService()
        val ret = service.get(1, 30, true)
        assertEquals(ret.size, 4)
    }

    @org.junit.Test
    fun get_with_vararg() {
        val service = NoteService()
        val ret = service.get(3, 2, true, 1, 2)
        assertEquals(ret.size, 4)
    }

    @org.junit.Test
    fun getById() {
        val service = NoteService()
        val ret = service.getById(5)
        assertTrue(ret is Note)
    }

    @org.junit.Test
    fun getComments() {
        val service = NoteService()
        val ret = service.getComments(2, 1, 2, true)
        assertEquals(ret.size, 2)
    }

    @org.junit.Test
    fun getComments_with_bad_offset() {
        val service = NoteService()
        val ret = service.getComments(2, 120, 2, true)
        assertTrue(ret.isEmpty())
    }

    @org.junit.Test
    fun getComments_with_bad_count() {
        val service = NoteService()
        val ret = service.getComments(2, 3, 20, true)
        assertEquals(ret.size, 2)
    }

    @org.junit.Test
    fun restore_deleted_comment() {
        val service = NoteService()
        val ret = service.restoreComment(13)
        assertTrue(ret)
    }

    @org.junit.Test
    fun restore_not_deleted_comment() {
        val service = NoteService()
        val ret = service.restoreComment(1)
        assertTrue(ret)
    }

    @org.junit.Test
    fun restore_not_existing_comment() {
        val service = NoteService()
        val ret = service.restoreComment(100500)
        assertFalse(ret)
    }
}