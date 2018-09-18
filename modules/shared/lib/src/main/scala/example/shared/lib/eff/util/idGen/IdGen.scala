package example.shared.lib.eff.util.idGen

import jp.eigosapuri.es.shared.lib.dddSupport.domain.{ IdGenerator, Identifier, UUIDIdGenerator }

sealed abstract class IdGen[+A]

object IdGen extends IdGenCreation {

  case class Generate[A <: Identifier[String]](
    generator: IdGenerator[A]
  ) extends IdGen[A]
}
