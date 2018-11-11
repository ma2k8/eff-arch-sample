package example.exampleApi.usecase.user.show

import cats._, cats.data._, cats.implicits._
import cats.instances.all._

import org.atnos.eff.Eff

import example.exampleApi.domain.repository.user.UserRepository
import example.shared.lib.dddSupport.Error.UseCaseError
import example.shared.lib.dddSupport.ErrorCode

import scala.concurrent.ExecutionContext
import example.shared.lib.eff._
import javax.inject.Inject

class ShowUserUseCaseImpl @Inject()(
  userRepo: UserRepository
) extends ShowUserUseCase {
  override def execute[R: _trantask: _errorEither](
    arg: ShowUserUseCaseArgs
  )(
    implicit ec: ExecutionContext
  ): Eff[R, ShowUserUseCaseResult] = {
    for {
      userOpt <- userRepo.resolveById[R](arg.userId)
      user <- {
        fromEither(userOpt match {
          case Some(user) => Either.right(user)
          case None => Either.left(UseCaseError(ErrorCode.RESOURCE_NOT_FOUND))
        })
      }
    } yield ShowUserUseCaseResult(user)
  }

}
