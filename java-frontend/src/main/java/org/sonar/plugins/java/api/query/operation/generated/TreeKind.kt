package org.sonar.plugins.java.api.query.operation.generated

import org.sonar.plugins.java.api.tree.*

class TreeKind<T : Tree>(val kind: Tree.Kind) {
  companion object {
    val COMPILATION_UNIT: TreeKind<CompilationUnitTree> = TreeKind(Tree.Kind.COMPILATION_UNIT)
    val CLASS: TreeKind<ClassTree> = TreeKind(Tree.Kind.CLASS)
    val ENUM: TreeKind<ClassTree> = TreeKind(Tree.Kind.ENUM)
    val INTERFACE: TreeKind<ClassTree> = TreeKind(Tree.Kind.INTERFACE)
    val ANNOTATION_TYPE: TreeKind<ClassTree> = TreeKind(Tree.Kind.ANNOTATION_TYPE)
    val RECORD: TreeKind<ClassTree> = TreeKind(Tree.Kind.RECORD)
    val ENUM_CONSTANT: TreeKind<EnumConstantTree> = TreeKind(Tree.Kind.ENUM_CONSTANT)
    val INITIALIZER: TreeKind<BlockTree> = TreeKind(Tree.Kind.INITIALIZER)
    val STATIC_INITIALIZER: TreeKind<StaticInitializerTree> = TreeKind(Tree.Kind.STATIC_INITIALIZER)
    val CONSTRUCTOR: TreeKind<MethodTree> = TreeKind(Tree.Kind.CONSTRUCTOR)
    val METHOD: TreeKind<MethodTree> = TreeKind(Tree.Kind.METHOD)
    val BLOCK: TreeKind<BlockTree> = TreeKind(Tree.Kind.BLOCK)
    val EMPTY_STATEMENT: TreeKind<EmptyStatementTree> = TreeKind(Tree.Kind.EMPTY_STATEMENT)
    val LABELED_STATEMENT: TreeKind<LabeledStatementTree> = TreeKind(Tree.Kind.LABELED_STATEMENT)
    val EXPRESSION_STATEMENT: TreeKind<ExpressionStatementTree> = TreeKind(Tree.Kind.EXPRESSION_STATEMENT)
    val IF_STATEMENT: TreeKind<IfStatementTree> = TreeKind(Tree.Kind.IF_STATEMENT)
    val ASSERT_STATEMENT: TreeKind<AssertStatementTree> = TreeKind(Tree.Kind.ASSERT_STATEMENT)
    val SWITCH_STATEMENT: TreeKind<SwitchStatementTree> = TreeKind(Tree.Kind.SWITCH_STATEMENT)
    val SWITCH_EXPRESSION: TreeKind<SwitchExpressionTree> = TreeKind(Tree.Kind.SWITCH_EXPRESSION)
    val CASE_GROUP: TreeKind<CaseGroupTree> = TreeKind(Tree.Kind.CASE_GROUP)
    val CASE_LABEL: TreeKind<CaseLabelTree> = TreeKind(Tree.Kind.CASE_LABEL)
    val WHILE_STATEMENT: TreeKind<WhileStatementTree> = TreeKind(Tree.Kind.WHILE_STATEMENT)
    val DO_STATEMENT: TreeKind<DoWhileStatementTree> = TreeKind(Tree.Kind.DO_STATEMENT)
    val FOR_STATEMENT: TreeKind<ForStatementTree> = TreeKind(Tree.Kind.FOR_STATEMENT)
    val FOR_EACH_STATEMENT: TreeKind<ForEachStatement> = TreeKind(Tree.Kind.FOR_EACH_STATEMENT)
    val BREAK_STATEMENT: TreeKind<BreakStatementTree> = TreeKind(Tree.Kind.BREAK_STATEMENT)
    val YIELD_STATEMENT: TreeKind<YieldStatementTree> = TreeKind(Tree.Kind.YIELD_STATEMENT)
    val CONTINUE_STATEMENT: TreeKind<ContinueStatementTree> = TreeKind(Tree.Kind.CONTINUE_STATEMENT)
    val RETURN_STATEMENT: TreeKind<ReturnStatementTree> = TreeKind(Tree.Kind.RETURN_STATEMENT)
    val THROW_STATEMENT: TreeKind<ThrowStatementTree> = TreeKind(Tree.Kind.THROW_STATEMENT)
    val SYNCHRONIZED_STATEMENT: TreeKind<SynchronizedStatementTree> = TreeKind(Tree.Kind.SYNCHRONIZED_STATEMENT)
    val TRY_STATEMENT: TreeKind<TryStatementTree> = TreeKind(Tree.Kind.TRY_STATEMENT)
    val CATCH: TreeKind<CatchTree> = TreeKind(Tree.Kind.CATCH)
    val POSTFIX_INCREMENT: TreeKind<UnaryExpressionTree> = TreeKind(Tree.Kind.POSTFIX_INCREMENT)
    val POSTFIX_DECREMENT: TreeKind<UnaryExpressionTree> = TreeKind(Tree.Kind.POSTFIX_DECREMENT)
    val PREFIX_INCREMENT: TreeKind<UnaryExpressionTree> = TreeKind(Tree.Kind.PREFIX_INCREMENT)
    val PREFIX_DECREMENT: TreeKind<UnaryExpressionTree> = TreeKind(Tree.Kind.PREFIX_DECREMENT)
    val UNARY_PLUS: TreeKind<UnaryExpressionTree> = TreeKind(Tree.Kind.UNARY_PLUS)
    val UNARY_MINUS: TreeKind<UnaryExpressionTree> = TreeKind(Tree.Kind.UNARY_MINUS)
    val BITWISE_COMPLEMENT: TreeKind<UnaryExpressionTree> = TreeKind(Tree.Kind.BITWISE_COMPLEMENT)
    val LOGICAL_COMPLEMENT: TreeKind<UnaryExpressionTree> = TreeKind(Tree.Kind.LOGICAL_COMPLEMENT)
    val MULTIPLY: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.MULTIPLY)
    val DIVIDE: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.DIVIDE)
    val REMAINDER: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.REMAINDER)
    val PLUS: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.PLUS)
    val MINUS: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.MINUS)
    val LEFT_SHIFT: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.LEFT_SHIFT)
    val RIGHT_SHIFT: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.RIGHT_SHIFT)
    val UNSIGNED_RIGHT_SHIFT: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.UNSIGNED_RIGHT_SHIFT)
    val LESS_THAN: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.LESS_THAN)
    val GREATER_THAN: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.GREATER_THAN)
    val LESS_THAN_OR_EQUAL_TO: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.LESS_THAN_OR_EQUAL_TO)
    val GREATER_THAN_OR_EQUAL_TO: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.GREATER_THAN_OR_EQUAL_TO)
    val EQUAL_TO: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.EQUAL_TO)
    val NOT_EQUAL_TO: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.NOT_EQUAL_TO)
    val AND: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.AND)
    val XOR: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.XOR)
    val OR: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.OR)
    val CONDITIONAL_AND: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.CONDITIONAL_AND)
    val CONDITIONAL_OR: TreeKind<BinaryExpressionTree> = TreeKind(Tree.Kind.CONDITIONAL_OR)
    val CONDITIONAL_EXPRESSION: TreeKind<ConditionalExpressionTree> = TreeKind(Tree.Kind.CONDITIONAL_EXPRESSION)
    val ARRAY_ACCESS_EXPRESSION: TreeKind<ArrayAccessExpressionTree> = TreeKind(Tree.Kind.ARRAY_ACCESS_EXPRESSION)
    val MEMBER_SELECT: TreeKind<MemberSelectExpressionTree> = TreeKind(Tree.Kind.MEMBER_SELECT)
    val NEW_CLASS: TreeKind<NewClassTree> = TreeKind(Tree.Kind.NEW_CLASS)
    val NEW_ARRAY: TreeKind<NewArrayTree> = TreeKind(Tree.Kind.NEW_ARRAY)
    val METHOD_INVOCATION: TreeKind<MethodInvocationTree> = TreeKind(Tree.Kind.METHOD_INVOCATION)
    val TYPE_CAST: TreeKind<TypeCastTree> = TreeKind(Tree.Kind.TYPE_CAST)
    val INSTANCE_OF: TreeKind<InstanceOfTree> = TreeKind(Tree.Kind.INSTANCE_OF)
    val PATTERN_INSTANCE_OF: TreeKind<PatternInstanceOfTree> = TreeKind(Tree.Kind.PATTERN_INSTANCE_OF)
    val PARENTHESIZED_EXPRESSION: TreeKind<ParenthesizedTree> = TreeKind(Tree.Kind.PARENTHESIZED_EXPRESSION)
    val ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.ASSIGNMENT)
    val MULTIPLY_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.MULTIPLY_ASSIGNMENT)
    val DIVIDE_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.DIVIDE_ASSIGNMENT)
    val REMAINDER_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.REMAINDER_ASSIGNMENT)
    val PLUS_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.PLUS_ASSIGNMENT)
    val MINUS_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.MINUS_ASSIGNMENT)
    val LEFT_SHIFT_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.LEFT_SHIFT_ASSIGNMENT)
    val RIGHT_SHIFT_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.RIGHT_SHIFT_ASSIGNMENT)
    val UNSIGNED_RIGHT_SHIFT_ASSIGNMENT: TreeKind<AssignmentExpressionTree> =
      TreeKind(Tree.Kind.UNSIGNED_RIGHT_SHIFT_ASSIGNMENT)
    val AND_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.AND_ASSIGNMENT)
    val XOR_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.XOR_ASSIGNMENT)
    val OR_ASSIGNMENT: TreeKind<AssignmentExpressionTree> = TreeKind(Tree.Kind.OR_ASSIGNMENT)
    val INT_LITERAL: TreeKind<LiteralTree> = TreeKind(Tree.Kind.INT_LITERAL)
    val LONG_LITERAL: TreeKind<LiteralTree> = TreeKind(Tree.Kind.LONG_LITERAL)
    val FLOAT_LITERAL: TreeKind<LiteralTree> = TreeKind(Tree.Kind.FLOAT_LITERAL)
    val DOUBLE_LITERAL: TreeKind<LiteralTree> = TreeKind(Tree.Kind.DOUBLE_LITERAL)
    val BOOLEAN_LITERAL: TreeKind<LiteralTree> = TreeKind(Tree.Kind.BOOLEAN_LITERAL)
    val CHAR_LITERAL: TreeKind<LiteralTree> = TreeKind(Tree.Kind.CHAR_LITERAL)
    val STRING_LITERAL: TreeKind<LiteralTree> = TreeKind(Tree.Kind.STRING_LITERAL)
    val TEXT_BLOCK: TreeKind<LiteralTree> = TreeKind(Tree.Kind.TEXT_BLOCK)
    val NULL_LITERAL: TreeKind<LiteralTree> = TreeKind(Tree.Kind.NULL_LITERAL)
    val IDENTIFIER: TreeKind<IdentifierTree> = TreeKind(Tree.Kind.IDENTIFIER)
    val VAR_TYPE: TreeKind<VarTypeTree> = TreeKind(Tree.Kind.VAR_TYPE)
    val VARIABLE: TreeKind<VariableTree> = TreeKind(Tree.Kind.VARIABLE)
    val ARRAY_TYPE: TreeKind<ArrayTypeTree> = TreeKind(Tree.Kind.ARRAY_TYPE)
    val PARAMETERIZED_TYPE: TreeKind<ParameterizedTypeTree> = TreeKind(Tree.Kind.PARAMETERIZED_TYPE)
    val UNION_TYPE: TreeKind<UnionTypeTree> = TreeKind(Tree.Kind.UNION_TYPE)
    val UNBOUNDED_WILDCARD: TreeKind<WildcardTree> = TreeKind(Tree.Kind.UNBOUNDED_WILDCARD)
    val EXTENDS_WILDCARD: TreeKind<WildcardTree> = TreeKind(Tree.Kind.EXTENDS_WILDCARD)
    val SUPER_WILDCARD: TreeKind<WildcardTree> = TreeKind(Tree.Kind.SUPER_WILDCARD)
    val ANNOTATION: TreeKind<AnnotationTree> = TreeKind(Tree.Kind.ANNOTATION)
    val MODIFIERS: TreeKind<ModifiersTree> = TreeKind(Tree.Kind.MODIFIERS)
    val LAMBDA_EXPRESSION: TreeKind<LambdaExpressionTree> = TreeKind(Tree.Kind.LAMBDA_EXPRESSION)
    val PRIMITIVE_TYPE: TreeKind<PrimitiveTypeTree> = TreeKind(Tree.Kind.PRIMITIVE_TYPE)
    val TYPE_PARAMETER: TreeKind<TypeParameterTree> = TreeKind(Tree.Kind.TYPE_PARAMETER)
    val IMPORT: TreeKind<ImportTree> = TreeKind(Tree.Kind.IMPORT)
    val PACKAGE: TreeKind<PackageDeclarationTree> = TreeKind(Tree.Kind.PACKAGE)
    val MODULE: TreeKind<ModuleDeclarationTree> = TreeKind(Tree.Kind.MODULE)
    val REQUIRES_DIRECTIVE: TreeKind<RequiresDirectiveTree> = TreeKind(Tree.Kind.REQUIRES_DIRECTIVE)
    val EXPORTS_DIRECTIVE: TreeKind<ExportsDirectiveTree> = TreeKind(Tree.Kind.EXPORTS_DIRECTIVE)
    val OPENS_DIRECTIVE: TreeKind<OpensDirectiveTree> = TreeKind(Tree.Kind.OPENS_DIRECTIVE)
    val USES_DIRECTIVE: TreeKind<UsesDirectiveTree> = TreeKind(Tree.Kind.USES_DIRECTIVE)
    val PROVIDES_DIRECTIVE: TreeKind<ProvidesDirectiveTree> = TreeKind(Tree.Kind.PROVIDES_DIRECTIVE)
    val ARRAY_DIMENSION: TreeKind<ArrayDimensionTree> = TreeKind(Tree.Kind.ARRAY_DIMENSION)
    val TYPE_PATTERN: TreeKind<TypePatternTree> = TreeKind(Tree.Kind.TYPE_PATTERN)
    val GUARDED_PATTERN: TreeKind<GuardedPatternTree> = TreeKind(Tree.Kind.GUARDED_PATTERN)
    val NULL_PATTERN: TreeKind<NullPatternTree> = TreeKind(Tree.Kind.NULL_PATTERN)
    val DEFAULT_PATTERN: TreeKind<DefaultPatternTree> = TreeKind(Tree.Kind.DEFAULT_PATTERN)
    val RECORD_PATTERN: TreeKind<RecordPatternTree> = TreeKind(Tree.Kind.RECORD_PATTERN)
    val OTHER: TreeKind<Tree> = TreeKind(Tree.Kind.OTHER)
    val TOKEN: TreeKind<SyntaxToken> = TreeKind(Tree.Kind.TOKEN)
    val TRIVIA: TreeKind<SyntaxTrivia> = TreeKind(Tree.Kind.TRIVIA)
    val INFERED_TYPE: TreeKind<InferedTypeTree> = TreeKind(Tree.Kind.INFERED_TYPE)
    val TYPE_ARGUMENTS: TreeKind<TypeArguments> = TreeKind(Tree.Kind.TYPE_ARGUMENTS)
    val METHOD_REFERENCE: TreeKind<MethodReferenceTree> = TreeKind(Tree.Kind.METHOD_REFERENCE)
    val TYPE_PARAMETERS: TreeKind<TypeParameters> = TreeKind(Tree.Kind.TYPE_PARAMETERS)
    val ARGUMENTS: TreeKind<Arguments> = TreeKind(Tree.Kind.ARGUMENTS)
    val LIST: TreeKind<ListTree<*>> = TreeKind(Tree.Kind.LIST)
  }
}
