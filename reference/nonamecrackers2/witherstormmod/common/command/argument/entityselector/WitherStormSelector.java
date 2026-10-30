/*    */ package nonamecrackers2.witherstormmod.common.command.argument.entityselector;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.commands.arguments.selector.EntitySelector;
/*    */ import net.minecraft.commands.arguments.selector.EntitySelectorParser;
/*    */ import net.minecraft.commands.arguments.selector.options.EntitySelectorOptions;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraftforge.common.command.IEntitySelectorType;
/*    */ import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
/*    */ 
/*    */ 
/*    */ public class WitherStormSelector
/*    */   implements IEntitySelectorType
/*    */ {
/*    */   public EntitySelector build(EntitySelectorParser parser) throws CommandSyntaxException {
/* 23 */     parser.m_121237_(1);
/* 24 */     parser.m_121279_(true);
/* 25 */     parser.m_121268_(EntitySelectorParser.f_121197_);
/* 26 */     parser.m_121241_((EntityType)WitherStormModEntityTypes.WITHER_STORM.get());
/* 27 */     parser.m_121272_(Entity::m_6084_);
/* 28 */     StringReader reader = parser.m_121346_();
/* 29 */     parser.m_121270_((b, c) -> suggestOpenOptions(parser, b));
/* 30 */     if (reader.canRead() && reader.peek() == '[') {
/*    */       
/* 32 */       reader.skip();
/* 33 */       parser.m_121270_((b, c) -> suggestOptionsKeyOrClose(parser, b));
/* 34 */       parser.m_121317_();
/*    */     } 
/* 36 */     parser.m_121229_();
/* 37 */     return parser.m_121230_();
/*    */   }
/*    */ 
/*    */   
/*    */   private static CompletableFuture<Suggestions> suggestOpenOptions(EntitySelectorParser parser, SuggestionsBuilder builder) {
/* 42 */     builder.suggest(String.valueOf('['));
/* 43 */     return builder.buildFuture();
/*    */   }
/*    */ 
/*    */   
/*    */   private static CompletableFuture<Suggestions> suggestOptionsKeyOrClose(EntitySelectorParser parser, SuggestionsBuilder builder) {
/* 48 */     builder.suggest(String.valueOf(']'));
/* 49 */     EntitySelectorOptions.m_121440_(parser, builder);
/* 50 */     return builder.buildFuture();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Component getSuggestionTooltip() {
/* 56 */     return (Component)Component.m_237115_("argument.witherstormmod.entity.selector.wither_storm");
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\command\argument\entityselector\WitherStormSelector.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */