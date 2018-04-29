/*
 * Copyright 2010 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest.engine.gregor.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import org.pitest.functional.FCollection;
import org.pitest.functional.prelude.Prelude;
import org.pitest.help.Help;
import org.pitest.help.PitHelpError;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.mutators.ABSMutator;
//import org.pitest.mutationtest.engine.gregor.mutators.AODMutator1;
//import org.pitest.mutationtest.engine.gregor.mutators.AODMutator2;
import org.pitest.mutationtest.engine.gregor.mutators.AORMutator1;
import org.pitest.mutationtest.engine.gregor.mutators.AORMutator2;
import org.pitest.mutationtest.engine.gregor.mutators.AORMutator3;
import org.pitest.mutationtest.engine.gregor.mutators.AORMutator4;
import org.pitest.mutationtest.engine.gregor.mutators.ArgumentPropagationMutator;
import org.pitest.mutationtest.engine.gregor.mutators.BooleanFalseReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.BooleanTrueReturnValsMutator;
//import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutate5;
//import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutateConstantToAddOne;
//import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutateConstantToSubOne;
//import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutateConstantWith0;
//import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutateConstantWith1;
import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutator1;
import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutator2;
import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutator3;
import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutator4;
import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutator5;
import org.pitest.mutationtest.engine.gregor.mutators.CRCRMutator6;
import org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ConstructorCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.EmptyObjectReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.IncrementsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.InlineConstantMutator;
import org.pitest.mutationtest.engine.gregor.mutators.InvertNegsMutator;
//import org.pitest.mutationtest.engine.gregor.mutators.M1;
import org.pitest.mutationtest.engine.gregor.mutators.MathMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NonVoidMethodCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NullReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.OBBNMutator1;
import org.pitest.mutationtest.engine.gregor.mutators.PrimitiveReturnsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RORMutator1;
import org.pitest.mutationtest.engine.gregor.mutators.RORMutator2;
import org.pitest.mutationtest.engine.gregor.mutators.RORMutator3;
import org.pitest.mutationtest.engine.gregor.mutators.RORMutator4;
import org.pitest.mutationtest.engine.gregor.mutators.RORMutator5;
import org.pitest.mutationtest.engine.gregor.mutators.RemoveConditionalMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RemoveConditionalMutator.Choice;
import org.pitest.mutationtest.engine.gregor.mutators.ReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.UOIMutator1;
import org.pitest.mutationtest.engine.gregor.mutators.UOIMutator2;
import org.pitest.mutationtest.engine.gregor.mutators.UOIMutator3;
import org.pitest.mutationtest.engine.gregor.mutators.UOIMutator4;
import org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.NakedReceiverMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.RemoveIncrementsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.RemoveSwitchMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.SwitchMutator;

public final class Mutator {

  private static final Map<String, Iterable<MethodMutatorFactory>> MUTATORS = new LinkedHashMap<>();

    static {

        //add("AOD1", AODMutator1.AOD_MUTATOR1);
        //add("AOD2", new AODMutator2());
        add("AOR1", AORMutator1.AOR_MUTATOR1);
        add("AOR2", AORMutator2.AOR_MUTATOR2);
        add("AOR3", AORMutator3.AOR_MUTATOR3);
        add("AOR4", AORMutator4.AOR_MUTATOR4);
        add("ROR1", RORMutator1.ROR_MUTATOR1);
        add("ROR2", RORMutator2.ROR_MUTATOR2);
        add("ROR3", RORMutator3.ROR_MUTATOR3);
        add("ROR4", RORMutator4.ROR_MUTATOR4);
        add("ROR5", RORMutator5.ROR_MUTATOR5);
        add("CRCR1",CRCRMutator1.CRCR_MUTATOR1);
        add("CRCR2",CRCRMutator2.CRCR_MUTATOR2);
        add("CRCR3",CRCRMutator3.CRCR_MUTATOR3);
        add("CRCR4",CRCRMutator4.CRCR_MUTATOR4);
        add("CRCR5",CRCRMutator5.CRCR_MUTATOR5);
        add("CRCR6",CRCRMutator6.CRCR_MUTATOR6);
        add("OBBN", OBBNMutator1.OBBN_MUTATOR1);
        add("UOI1",UOIMutator1.UOI_MUTATOR1);
        add("UOI2",UOIMutator2.UOI_MUTATOR2);
        add("UOI3",UOIMutator3.UOI_MUTATOR3);
        add("UOI4",UOIMutator4.UOI_MUTATOR4);
        //add("M1",M1.M1);
        add("ABS",ABSMutator.ABS_MUTATOR);
        //addGroup("myMutators", myMutators());

        /**
         * Default mutator that inverts the negation of integer and floating point
         * numbers.
         */
        add("INVERT_NEGS", InvertNegsMutator.INVERT_NEGS_MUTATOR);

        /**
         * Default mutator that mutates the return values of methods.
         */
        add("RETURN_VALS", ReturnValsMutator.RETURN_VALS_MUTATOR);

        /**
         * Optional mutator that mutates integer and floating point inline constants.
         */
        add("INLINE_CONSTS", new InlineConstantMutator());

        /**
         * Default mutator that mutates binary arithmetic operations.
         */
        add("MATH", MathMutator.MATH_MUTATOR);

        /**
         * Default mutator that removes method calls to void methods.
         *
         */
        add("VOID_METHOD_CALLS", VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR);

        /**
         * Default mutator that negates conditionals.
         */
        add("NEGATE_CONDITIONALS", NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR);

        /**
         * Default mutator that replaces the relational operators with their boundary
         * counterpart.
         */
        add("CONDITIONALS_BOUNDARY", ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR);

        /**
         * Default mutator that mutates increments, decrements and assignment increments
         * and decrements of local variables.
         */
        add("INCREMENTS", IncrementsMutator.INCREMENTS_MUTATOR);

        /**
         * Optional mutator that removes local variable increments.
         */

        add("REMOVE_INCREMENTS", RemoveIncrementsMutator.REMOVE_INCREMENTS_MUTATOR);

        /**
         * Optional mutator that removes method calls to non void methods.
         */
        add("NON_VOID_METHOD_CALLS", NonVoidMethodCallMutator.NON_VOID_METHOD_CALL_MUTATOR);

        /**
         * Optional mutator that replaces constructor calls with null values.
         */
        add("CONSTRUCTOR_CALLS", ConstructorCallMutator.CONSTRUCTOR_CALL_MUTATOR);

        /**
         * Removes conditional statements so that guarded statements always execute The
         * EQUAL version ignores LT,LE,GT,GE, which is the default behaviour, ORDER
         * version mutates only those.
         */

        add("REMOVE_CONDITIONALS_EQ_IF", new RemoveConditionalMutator(Choice.EQUAL, true));
        add("REMOVE_CONDITIONALS_EQ_ELSE", new RemoveConditionalMutator(Choice.EQUAL, false));
        add("REMOVE_CONDITIONALS_ORD_IF", new RemoveConditionalMutator(Choice.ORDER, true));
        add("REMOVE_CONDITIONALS_ORD_ELSE", new RemoveConditionalMutator(Choice.ORDER, false));
        addGroup("REMOVE_CONDITIONALS", RemoveConditionalMutator.makeMutators());

        add("TRUE_RETURNS", BooleanTrueReturnValsMutator.BOOLEAN_TRUE_RETURN);
        add("FALSE_RETURNS", BooleanFalseReturnValsMutator.BOOLEAN_FALSE_RETURN);
        add("PRIMITIVE_RETURNS", PrimitiveReturnsMutator.PRIMITIVE_RETURN_VALS_MUTATOR);
        add("EMPTY_RETURNS", EmptyObjectReturnValsMutator.EMPTY_RETURN_VALUES);
        add("NULL_RETURNS", NullReturnValsMutator.NULL_RETURN_VALUES);
        addGroup("RETURNS", betterReturns());

        /**
         * Experimental mutator that removed assignments to member variables.
         */
        add("EXPERIMENTAL_MEMBER_VARIABLE",
                new org.pitest.mutationtest.engine.gregor.mutators.experimental.MemberVariableMutator());

        /**
         * Experimental mutator that swaps labels in switch statements
         */
        add("EXPERIMENTAL_SWITCH", new org.pitest.mutationtest.engine.gregor.mutators.experimental.SwitchMutator());

        /**
         * Experimental mutator that replaces method call with one of its parameters of
         * matching type
         */
        add("EXPERIMENTAL_ARGUMENT_PROPAGATION", ArgumentPropagationMutator.ARGUMENT_PROPAGATION_MUTATOR);

        /**
         * Experimental mutator that replaces method call with this
         */
        add("EXPERIMENTAL_NAKED_RECEIVER", NakedReceiverMutator.NAKED_RECEIVER);

        addGroup("REMOVE_SWITCH", RemoveSwitchMutator.makeMutators());
        addGroup("DEFAULTS", defaults());
        addGroup("STRONGER", stronger());
        addGroup("ALL", all());
        addGroup("NEW_DEFAULTS", newDefaults());
    }

  public static Collection<MethodMutatorFactory> all() {
    return fromStrings(MUTATORS.keySet());
  }

  private static Collection<MethodMutatorFactory> stronger() {
    return combine(
        defaults(),
        group(new RemoveConditionalMutator(Choice.EQUAL, false),
            new SwitchMutator()));
  }

  private static Collection<MethodMutatorFactory> combine(
      Collection<MethodMutatorFactory> a, Collection<MethodMutatorFactory> b) {
    final List<MethodMutatorFactory> l = new ArrayList<>(a);
    l.addAll(b);
    return l;
  }

  /**
   * Default set of mutators - designed to provide balance between strength and
   * performance
   */
  public static Collection<MethodMutatorFactory> defaults() {
    return group(InvertNegsMutator.INVERT_NEGS_MUTATOR,
        ReturnValsMutator.RETURN_VALS_MUTATOR, MathMutator.MATH_MUTATOR,
        VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR,
        NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR,
        ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR,
        IncrementsMutator.INCREMENTS_MUTATOR,
        //AODMutator1.AOD_MUTATOR1,
        //new AODMutator2(),
        AORMutator1.AOR_MUTATOR1,
        AORMutator2.AOR_MUTATOR2,
        AORMutator3.AOR_MUTATOR3,
        AORMutator4.AOR_MUTATOR4,
        RORMutator1.ROR_MUTATOR1,
        RORMutator2.ROR_MUTATOR2,
        RORMutator3.ROR_MUTATOR3,
        RORMutator4.ROR_MUTATOR4,
        RORMutator5.ROR_MUTATOR5,
        //M1.M1,
        CRCRMutator1.CRCR_MUTATOR1,
        CRCRMutator2.CRCR_MUTATOR2,
        CRCRMutator3.CRCR_MUTATOR3,
        CRCRMutator4.CRCR_MUTATOR4,
        CRCRMutator5.CRCR_MUTATOR5,
        CRCRMutator6.CRCR_MUTATOR6,
        OBBNMutator1.OBBN_MUTATOR1,
        UOIMutator1.UOI_MUTATOR1,
        UOIMutator2.UOI_MUTATOR2,
        UOIMutator3.UOI_MUTATOR3,
        UOIMutator4.UOI_MUTATOR4,
        ABSMutator.ABS_MUTATOR
        );
  }

  /**
   * Proposed new defaults - replaced the RETURN_VALS mutator with the new more stable set
   */
  public static Collection<MethodMutatorFactory> newDefaults() {
    return combine(group(InvertNegsMutator.INVERT_NEGS_MUTATOR,
        MathMutator.MATH_MUTATOR,
        VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR,
        NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR,
        ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR,
        IncrementsMutator.INCREMENTS_MUTATOR), betterReturns());
  }


  public static Collection<MethodMutatorFactory> betterReturns() {
    return group(BooleanTrueReturnValsMutator.BOOLEAN_TRUE_RETURN,
        BooleanFalseReturnValsMutator.BOOLEAN_FALSE_RETURN,
        PrimitiveReturnsMutator.PRIMITIVE_RETURN_VALS_MUTATOR,
        EmptyObjectReturnValsMutator.EMPTY_RETURN_VALUES,
        NullReturnValsMutator.NULL_RETURN_VALUES
       );
  }
  
//  public static Collection<MethodMutatorFactory> myMutators() {
//      return group(AODMutator1.AOD_MUTATOR1,
//              new AODMutator2(),
//              AORMutator1.AOR_MUTATOR1,
//              AORMutator2.AOR_MUTATOR2,
//              AORMutator3.AOR_MUTATOR3,
//              AORMutator4.AOR_MUTATOR4,
//              RORMutator1.ROR_MUTATOR1,
//              RORMutator2.ROR_MUTATOR2,
//              RORMutator3.ROR_MUTATOR3,
//              RORMutator4.ROR_MUTATOR4,
//              RORMutator5.ROR_MUTATOR5,
//              M1.M1
//          );
//    }

  private static Collection<MethodMutatorFactory> group(
      final MethodMutatorFactory... ms) {
    return Arrays.asList(ms);
  }

  public static Collection<MethodMutatorFactory> byName(final String name) {
    return FCollection.map(MUTATORS.get(name),
        Prelude.id(MethodMutatorFactory.class));
  }

  private static void add(final String key, final MethodMutatorFactory value) {
    MUTATORS.put(key, Collections.singleton(value));
  }

  private static void addGroup(final String key,
      final Iterable<MethodMutatorFactory> value) {
    MUTATORS.put(key, value);
  }

  public static Collection<MethodMutatorFactory> fromStrings(
      final Collection<String> names) {
    final Set<MethodMutatorFactory> unique = new TreeSet<>(
        compareId());

    FCollection.flatMapTo(names, fromString(), unique);
    return unique;
  }

  private static Comparator<? super MethodMutatorFactory> compareId() {
    return (o1, o2) -> o1.getGloballyUniqueId().compareTo(o2.getGloballyUniqueId());
  }

  private static Function<String, Iterable<MethodMutatorFactory>> fromString() {
    return a -> {
      final Iterable<MethodMutatorFactory> i = MUTATORS.get(a);
      if (i == null) {
        throw new PitHelpError(Help.UNKNOWN_MUTATOR, a);
      }
      return i;
    };
  }

}
