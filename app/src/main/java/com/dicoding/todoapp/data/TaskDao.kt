package com.dicoding.todoapp.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.sqlite.db.SupportSQLiteQuery

//TODO 2 : Define data access object (DAO)
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getTasks(query: SupportSQLiteQuery): DataSource.Factory<Int, Task>
    @Query("SELECT * FROM tasks WHERE id = :taskId")

    fun getTaskById(taskId: Int): LiveData<Task>
    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY dueDateMillis ASC LIMIT 1")

    fun getNearestActiveTask(): Task
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insertTask(task: Task): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    fun insertAll(vararg tasks: Task)
    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTask(taskId: Task)
    @Query("UPDATE tasks SET isCompleted = :completed WHERE id = :taskId")
    suspend fun updateCompleted(taskId: Int, completed: Boolean)
    
}
