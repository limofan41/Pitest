package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.ArrayList;
//import java.io.IOException;
import java.util.Optional;

//import org.assertj.core.internal.asm.ClassReader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
//import org.pitest.mutationtest.engine.gregor.GregorMutater;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

import bsh.org.objectweb.asm.Type;

public class M2mutator extends MethodVisitor {
    private final MethodMutatorFactory factory;
    private final MutationContext context;
    private final ClassByteArraySource byteSource;

    M2mutator(final MutationContext context, final MethodVisitor vistor, final MethodMutatorFactory factory,
            final ClassByteArraySource byteSource) {
        super(Opcodes.ASM6, vistor);
        this.factory = factory;
        this.context = context;
        this.byteSource = byteSource;

    }

    @Override
    public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc,
           final boolean itf) {
        Optional <byte[]> bytes = byteSource.getBytes(owner);
        if (bytes.isPresent()) {
            final ClassReader cr = new ClassReader(bytes.get());          
        }
        
       
    }

    class M2ClassVisitor extends ClassVisitor {
        M2ClassVisitor() {
            super(Opcodes.ASM6);
        }
    }
    public ArrayList<MethodInfo> findloadmethodsofusingone(String owner, String name, String desc, ArrayList<MethodInfo> overload, MethodInfo usingMethod) {
        ArrayList<MethodInfo> returnmethods = new ArrayList<>();
      //check org.objectweb.asm.Type  
        //Returns the Java types corresponding to the argument types of the given method descriptor.
        Type [] typeofusingMehod = Type.getArgumentTypes(desc); //↑
                for (MethodInfo mi : overload) {
                    Type [] typeofoverload = Type.getArgumentTypes(mi.getMethodDescriptor());
                    if (mi.getName().equals(usingMethod.getName())
                           && mi.getReturnType().equals(usingMethod.getReturnType())
                           && typeofusingMehod.length != typeofoverload.length
                           ) {
                        returnmethods.add(mi);
                    }
                }
        return returnmethods;
    }

}
