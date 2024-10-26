import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.truthoraction.DataPlayers.AppDataBasePlayer
import kotlinx.coroutines.runBlocking

class ClearDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private val db: AppDataBasePlayer = AppDataBasePlayer.getDatabase(context)
    private val playerDao = db.playerDao()

    override fun doWork(): Result {
        runBlocking {
            playerDao.deleteAllPlayers()  // Очищення БД
        }
        return Result.success()
    }
}