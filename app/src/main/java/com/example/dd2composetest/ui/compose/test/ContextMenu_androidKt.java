package com.example.dd2composetest.ui.compose.test;


import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableInferredTarget;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
        mv = {1, 6, 0},
        k = 2,
        xi = 48,
        d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u0007\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\t¨\u0006\n"},
        d2 = {"ContextMenuArea", "", "manager", "Landroidx/compose/foundation/text/selection/SelectionManager;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/selection/SelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "foundation_release"}
)
public final class ContextMenu_androidKt {
    @Composable
    @ComposableInferredTarget(
            scheme = "[0[0]]"
    )
    public static final void ContextMenuArea(@NotNull final TextFieldSelectionManager manager, @NotNull final Function2 content, @Nullable Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(content, "content");
        $composer = $composer.startRestartGroup(-1985516685);
        ComposerKt.sourceInformation($composer, "C(ContextMenuArea)P(1)27@979L9:ContextMenu.android.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 112) == 0) {
            $dirty = $changed | ($composer.changed(content) ? 32 : 16);
        }

        if (($dirty & 81) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            content.invoke($composer, 14 & $dirty >> 3);
        }

        ScopeUpdateScope var10000 = $composer.endRestartGroup();
        if (var10000 != null) {
            var10000.updateScope((Function2)(new Function2() {
                public final void invoke(@Nullable Composer $composer, int $force) {
                    ContextMenuArea(manager, content, $composer, $changed | 1);
                }

                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object p1, Object p2) {
                    this.invoke((Composer)p1, ((Number)p2).intValue());
                    return Unit.INSTANCE;
                }
            }));
        }

    }

    @Composable
    @ComposableInferredTarget(
            scheme = "[0[0]]"
    )
    public static final void ContextMenuArea(@NotNull final SelectionManager manager, @NotNull final Function2 content, @Nullable Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(content, "content");
        $composer = $composer.startRestartGroup(605522716);
        ComposerKt.sourceInformation($composer, "C(ContextMenuArea)P(1)35@1116L9:ContextMenu.android.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 112) == 0) {
            $dirty = $changed | ($composer.changed(content) ? 32 : 16);
        }

        if (($dirty & 81) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            content.invoke($composer, 14 & $dirty >> 3);
        }

        ScopeUpdateScope var10000 = $composer.endRestartGroup();
        if (var10000 != null) {
            var10000.updateScope((Function2)(new Function2() {
                public final void invoke(@Nullable Composer $composer, int $force) {
                    ContextMenuArea(manager, content, $composer, $changed | 1);
                }

                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object p1, Object p2) {
                    this.invoke((Composer)p1, ((Number)p2).intValue());
                    return Unit.INSTANCE;
                }
            }));
        }

    }
}
